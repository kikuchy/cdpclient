plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.7.2")
}