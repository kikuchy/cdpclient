package net.kikuchy

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import net.kikuchy.cdpclient.CDPClient
import net.kikuchy.cdpclient.domain.Page
import net.kikuchy.cdpclient.domain.page
import net.kikuchy.cdpclient.domain.target


@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        CDPClient.use("localhost", 9222, "devtools/browser/59119c99-872c-458c-aa2d-91cf1f797f82") {
            val job = async {
                this@use.target.targetCreated.collect { (targetInfo) ->
                    println(targetInfo)
                }
            }
            target.setDiscoverTargets(true)
            val (targetID) = target.createTarget("http://example.com", width = 800, height = 800)
            println(targetID)
            val (targetID2) = target.createTarget("https://www.google.com")
            println(targetID2)
            val (targetInfo) = target.getTargetInfo(targetID)
            println(targetInfo)
            job.cancel()
        }
    }
}