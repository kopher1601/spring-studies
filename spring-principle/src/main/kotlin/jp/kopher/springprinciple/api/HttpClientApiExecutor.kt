package jp.kopher.springprinciple.api

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpClientApiExecutor : ApiExecutor {
    override fun execute(uri: URI): String {
        val request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build()

        return HttpClient.newBuilder()
            .build()
            .use { client ->
                client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body()
            }
    }
}