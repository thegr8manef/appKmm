package com.example.appkmm

import com.mobelite.corelibkmm.src.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI

class Greeting {

    private lateinit var postData: HttpResponse
    suspend fun greeting(): String {
        val client = HttpClient()
        try {
            postData = client.post(Constants.BASE_URL) {
                contentType(ContentType.Application.Json)
                setBody("""{"value": "0"}""") // Send 1 as the value)
            }

        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
        } finally {
            client.close()
        }
        return postData.bodyAsText()
    }
}