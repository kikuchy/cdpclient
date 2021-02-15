import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

const val PACKAGE_NAME = "net.kikuchy.cdpclient.domain"
const val BASE_PACKAGE_NAME = "net.kikuchy.cdpclient"
val CDPCLIENT = ClassName(BASE_PACKAGE_NAME, "CDPClient")
val DOMAIN = ClassName(BASE_PACKAGE_NAME, "Domain")
val FLOW = ClassName("kotlinx.coroutines.flow", "Flow")
val JSON = ClassName("kotlinx.serialization.json", "Json")
val SERIALIZABLE = ClassName("kotlinx.serialization", "Serializable")
val SERIALNAME = ClassName("kotlinx.serialization", "SerialName")
val JSONELEMENT = ClassName("kotlinx.serialization.json", "JsonElement")
val EXPERIMENTAL_COROUTINE_API = ClassName("kotlinx.coroutines", "ExperimentalCoroutinesApi")

fun Domain.generateClassFile(domains: List<Domain>): FileSpec {
    val domainClass = TypeSpec.classBuilder(domain).apply {
        addSuperinterface(DOMAIN)
        primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("client", CDPCLIENT, KModifier.PRIVATE)
                .build()
        )
        addProperty(
            PropertySpec.builder("client", CDPCLIENT, KModifier.PRIVATE)
                .initializer("client")
                .build()
        )

        description?.let { addKdoc(it) }
        types.forEach {
            it.generateTypeClass(this@generateClassFile, domains)?.let {
                addType(it)
            }
        }
        events.forEach {
            // TODO: イベントの実装どうするか
            addProperty(it.generateEventChanel(this@generateClassFile, domains))
            it.generateEventParameter(this@generateClassFile, domains)?.let {
                addType(it)
            }
        }
        commands.forEach {
            addFunction(it.generateMethod(this@generateClassFile, domains))
            it.generateParameterExpandedMethod(this@generateClassFile, domains)?.let {
                addFunction(it)
            }
            it.generateParameterClass(this@generateClassFile, domains)?.let {
                addType(it)
            }
            it.generateReturnClass(this@generateClassFile, domains)?.let {
                addType(it)
            }
        }
    }
        .build()
    val file = FileSpec.builder(PACKAGE_NAME, domain)
        .addImport("kotlinx.serialization.json", "decodeFromJsonElement", "encodeToJsonElement")
        .addImport("kotlinx.coroutines.flow", "filter", "filterNotNull", "map")
        .addProperty(
            PropertySpec.builder(domain.toLowerCamelCase(), ClassName(PACKAGE_NAME, domain))
                .receiver(CDPCLIENT)
                .getter(
                    FunSpec.getterBuilder()
                        .addCode("return getGeneratedDomain() ?: cacheGeneratedDomain($domain(this))")
                        .build()
                )
                .build()
        )
        .addType(
            domainClass
        ).build()
    return file
}

fun Domain.Type.generateTypeClass(parentDomain: Domain, domains: List<Domain>): TypeSpec? {
    return when (type) {
        "object" -> if (properties.isNotEmpty()) {
            TypeSpec.classBuilder(id).apply {
                addModifiers(KModifier.DATA)
                description?.let { addKdoc(it) }
                addAnnotation(SERIALIZABLE)
                properties.forEach {
                    addProperty(it.generateTypeProperty(parentDomain, domains))
                }
                primaryConstructor(FunSpec.constructorBuilder().apply {
                    properties.forEach {
                        addParameter(ParameterSpec.builder(it.name, it.resolveType(parentDomain, domains))
                            .apply {
                                if (it.optional) {
                                    defaultValue("null")
                                }
                            }
                            .build())
                    }
                }.build())
            }.build()
        } else null
        "string" -> if (enum.isNotEmpty()) {
            TypeSpec.enumBuilder(id).apply {
                description?.let { addKdoc(it) }
                addAnnotation(SERIALIZABLE)
                enum.forEach {
                    // TODO: SerialName
                    addEnumConstant(
                        it.toCapitalCase(), TypeSpec.anonymousClassBuilder()
                            .addAnnotation(
                                AnnotationSpec.builder(SERIALNAME)
                                    .addMember("%S", it)
                                    .build()
                            )
                            .build()
                    )
                }
            }.build()
        } else null
        else -> null
    }
}

fun Domain.Type.Property.generateTypeProperty(parentDomain: Domain, domains: List<Domain>): PropertySpec {
    val typeName = resolveType(parentDomain, domains)
    return PropertySpec.builder(name, typeName)
        .initializer(name)
        .apply {
            description?.let { addKdoc(it) }
        }.build()
}

fun List<Domain>.resolveRef(refName: String, parentDomain: Domain): Pair<Domain, Domain.Type> {
    return try {
        if (refName.contains('.')) {
            val p = refName.split('.')
            val domainName = p.first()
            val typeName = p.last()
            val domainBelongs = this.single { it.domain == domainName }
            val found = domainBelongs.types
                .single { it.id == typeName }
            domainBelongs to found
        } else {
            parentDomain to parentDomain.types.single { it.id == refName }
        }
    } catch (e: Exception) {
        println(refName)
        println(parentDomain.domain)
        throw e
    }
}

fun Domain.CanBeTypeAlias.jsTypeToKType(): TypeName {
    return when (type!!) {
        "number" -> DOUBLE
        "string" -> STRING
        "integer" -> INT
        "boolean" -> BOOLEAN
        "any" -> JSONELEMENT
        "object" -> MAP.parameterizedBy(STRING, JSONELEMENT)
        "array" -> LIST.parameterizedBy(DOUBLE)
        else -> error("タイプを解釈できなかったでした $this $type")
    }
}

fun Domain.TypeOrReference.jsTypeToKType(parentDomain: Domain, domains: List<Domain>): TypeName {
    return when (type!!) {
        "number" -> DOUBLE
        "string" -> STRING
        "integer" -> INT
        "boolean" -> BOOLEAN
        "array" -> if (items.containsKey("\$ref")) {
            val referenceName = items["\$ref"]
            val className = object : Domain.TypeOrReference {
                override val ref: String? = referenceName
                override val items: Map<String, String> = emptyMap()
                override val optional: Boolean = false
                override val type: String? = null
            }.resolveType(parentDomain, domains)
            LIST.parameterizedBy(className)
        } else {
            when (items["type"]) {
                "string" -> STRING
                "integer" -> INT
                "number" -> DOUBLE
                "any" -> JSONELEMENT
                "object" -> MAP.parameterizedBy(STRING, JSONELEMENT)
                else -> error("知らんがな ${parentDomain.domain} $items")
            }
        }
        "any" -> JSONELEMENT
        "object" -> MAP.parameterizedBy(STRING, JSONELEMENT)
        else -> error("タイプを解釈できなかったでした ${parentDomain.domain} $type")
    }
}

fun Domain.TypeOrReference.resolveType(parentDomain: Domain, domains: List<Domain>): TypeName {
    return if (this.ref != null) {
        val (domain, type) = domains.resolveRef(this.ref!!, parentDomain)
        if ((type.type == "object" && type.properties.isNotEmpty()) || (type.type == "string" && type.enum.isNotEmpty())) {
            if (domain == parentDomain) {
                ClassName("", type.id)
            } else {
                ClassName(PACKAGE_NAME, domain.domain).nestedClass(type.id)
            }
        } else {
            type.jsTypeToKType()
        }
    } else {
        jsTypeToKType(parentDomain, domains)
    }.copy(nullable = optional)
}

fun Domain.Event.generateEventChanel(parentDomain: Domain, domains: List<Domain>): PropertySpec {
    return PropertySpec.builder(name, FLOW.parameterizedBy(parameterTypeName))
        .addAnnotation(EXPERIMENTAL_COROUTINE_API)
        .initializer(
            """
            client
                .events
                .filter {
                    it.method == %S
                }
                .map {
                    it.params
                }
                .filterNotNull()
                .map {
                    %T.decodeFromJsonElement(it)
                }
        """.trimIndent(), name, JSON
        )
        .build()
}

fun Domain.Event.generateEventParameter(parentDomain: Domain, domains: List<Domain>): TypeSpec? {
    return if (parameters.isNotEmpty()) {
        TypeSpec.classBuilder(parameterRawTypeName).apply {
            description?.let { addKdoc(it) }
            addModifiers(KModifier.DATA)
            primaryConstructor(FunSpec.constructorBuilder().apply {
                this@generateEventParameter.parameters.forEach {
                    addParameter(ParameterSpec.builder(it.name, it.resolveType(parentDomain, domains))
                        .apply {
                            if (it.optional) {
                                defaultValue("null")
                            }
                        }
                        .build())
                }
            }.build())
            parameters.forEach {
                addProperty(
                    PropertySpec.builder(it.name, it.resolveType(parentDomain, domains))
                        .initializer(it.name)
                        .apply { it.description?.let { addKdoc(it) } }
                        .build()
                )
            }
        }
            .build()
    } else {
        null
    }
}

val Domain.Event.parameterRawTypeName: String
    get() = "${name.capitalize()}Parameter"
val Domain.Event.parameterTypeName: TypeName
    get() = if (parameters.isNotEmpty()) {
        ClassName("", parameterRawTypeName)
    } else {
        UNIT
    }

fun Domain.Command.generateMethod(parentDomain: Domain, domains: List<Domain>): FunSpec {
    return FunSpec.builder(name)
        .addAnnotation(EXPERIMENTAL_COROUTINE_API)
        .addModifiers(KModifier.SUSPEND).apply {
            description?.let { addKdoc(it) }
            if (deprecated) {
                addAnnotation(
                    AnnotationSpec.builder(Deprecated::class)
                        .addMember("message = %S", "")
                        .build()
                )
            }

            // parameters
            if (this@generateMethod.parameters.isEmpty()) {
                // no parameter
            } else {
                addParameter("args", this@generateMethod.parameterTypeName)
            }

            // returning values
            if (returns.isEmpty()) {
                // return Unit
            } else {
                returns(this@generateMethod.returnTypeName)
            }

            // calling command
            addCode(CodeBlock.builder().apply {
                if (this@generateMethod.parameters.isEmpty()) {
                    addStatement("val parameter = null")
                } else {
                    addStatement("val parameter = %T { encodeDefaults = false }.encodeToJsonElement(args)", JSON)
                }
                if (this@generateMethod.returns.isEmpty()) {
                    addStatement("client.callCommand(\"${parentDomain.domain}.$name\", parameter)")
                } else {
                    addStatement("val result = client.callCommand(\"${parentDomain.domain}.$name\", parameter)")
                }
                if (this@generateMethod.returns.isNotEmpty()) {
                    addStatement("return result!!.let { %T.decodeFromJsonElement(it) }", JSON)
                }
            }.build())
        }.build()
}

fun Domain.Command.generateParameterExpandedMethod(parentDomain: Domain, domains: List<Domain>): FunSpec? {
    return if (parameters.isNotEmpty()) {
        FunSpec.builder(name)
            .addModifiers(KModifier.SUSPEND)
            .apply {
                this@generateParameterExpandedMethod.parameters.forEach {
                    addParameter(ParameterSpec.builder(it.name, it.resolveType(parentDomain, domains))
                        .apply {
                            if (it.optional) {
                                defaultValue("null")
                            }
                        }
                        .build())
                }
                addCode(CodeBlock.builder()
                    .apply {
                        val paramList = this@generateParameterExpandedMethod.parameters.map {
                            "${it.name} = ${it.name}"
                        }.joinToString(",")
                        addStatement(
                            "val parameter = %T($paramList)",
                            this@generateParameterExpandedMethod.parameterTypeName
                        )
                        if (this@generateParameterExpandedMethod.returns.isNotEmpty()) {
                            addStatement("return $name(parameter)")
                        } else {
                            addStatement("$name(parameter)")
                        }
                    }
                    .build())
                if (this@generateParameterExpandedMethod.returns.isNotEmpty()) {
                    returns(returnTypeName)
                }
            }
            .build()
    } else {
        null
    }
}

val Domain.Command.parameterTypeRawName: String
    get() = "${name.capitalize()}Parameter"
val Domain.Command.parameterTypeName: TypeName
    get() = ClassName("", parameterTypeRawName)

fun Domain.Command.generateParameterClass(parentDomain: Domain, domains: List<Domain>): TypeSpec? {
    return if (parameters.isNotEmpty()) {
        TypeSpec.classBuilder(parameterTypeRawName)
            .addModifiers(KModifier.DATA)
            .addAnnotation(SERIALIZABLE)
            .primaryConstructor(FunSpec.constructorBuilder()
                .apply {
                    this@generateParameterClass.parameters.forEach {
                        addParameter(ParameterSpec.builder(it.name, it.resolveType(parentDomain, domains))
                            .apply {
                                if (it.optional) {
                                    defaultValue("null")
                                }
                            }
                            .build())
                    }
                }
                .build())
            .apply {
                parameters.forEach {
                    addProperty(PropertySpec.builder(it.name, it.resolveType(parentDomain, domains))
                        .apply {
                            it.description?.let { addKdoc(it) }
                        }
                        .initializer(it.name)
                        .build())
                }
            }
            .build()
    } else {
        null
    }
}

val Domain.Command.returnTypeRawName: String
    get() = "${name.capitalize()}Return"
val Domain.Command.returnTypeName: TypeName
    get() = ClassName("", returnTypeRawName)

fun Domain.Command.generateReturnClass(parentDomain: Domain, domains: List<Domain>): TypeSpec? {
    return if (returns.isNotEmpty()) {
        TypeSpec.classBuilder(returnTypeRawName)
            .addModifiers(KModifier.DATA)
            .addAnnotation(SERIALIZABLE)
            .primaryConstructor(FunSpec.constructorBuilder()
                .apply {
                    this@generateReturnClass.returns.forEach {
                        addParameter(it.name, it.resolveType(parentDomain, domains))
                    }
                }
                .build())
            .apply {
                returns.forEach {
                    addProperty(PropertySpec.builder(it.name, it.resolveType(parentDomain, domains))
                        .apply {
                            it.description?.let { addKdoc(it.escapePercentage()) }
                        }
                        .initializer(it.name)
                        .build())
                }
            }
            .build()
    } else {
        null
    }
}

fun String.escapePercentage(): String = replace("%", "%%")
fun String.toLowerCamelCase(): String {
    return if (all { it.isUpperCase() }) {
        toLowerCase()
    } else {
        decapitalize()
    }
}

fun String.toCapitalCase(): String {
    return String(map { it.toUpperCase() }.toCharArray()).replace('-', '_')
}