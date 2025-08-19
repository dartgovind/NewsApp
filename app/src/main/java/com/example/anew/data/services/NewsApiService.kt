package com.example.anew.data.services

import com.example.anew.data.model.NewsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NewsApiService(private val apiKey: String) {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "newsapi.org"
                path("v2", "top-headlines")
                parameters.append("country", "us")
                parameters.append("category", "business")
            }
        }
    }

    suspend fun getNews(): NewsDto {
        return try {
            client.get {
                parameter("apiKey", apiKey)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            throw  Exception("Failed to fetch News data: ${e.localizedMessage}")
        }
    }
}
