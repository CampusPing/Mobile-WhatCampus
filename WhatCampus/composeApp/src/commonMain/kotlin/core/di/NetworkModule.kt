package core.di

import com.campus.whatcampus.BuildKonfig
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val TIMEOUT_DURATION: Long = 60_000L

val networkModule = module {
    single {
        HttpClient {
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT_DURATION
                requestTimeoutMillis = TIMEOUT_DURATION
                socketTimeoutMillis = TIMEOUT_DURATION
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "WhatCampus") { "Ktor: $message" }
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            defaultRequest {
                url(BuildKonfig.BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }
}
