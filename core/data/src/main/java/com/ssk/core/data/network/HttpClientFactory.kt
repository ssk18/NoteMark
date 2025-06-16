package com.ssk.core.data.network

import com.ssk.core.data.Routes
import com.ssk.core.data.models.AccessTokenRequest
import com.ssk.core.data.models.AccessTokenResponse
import com.ssk.core.data.models.Constants
import com.ssk.core.domain.Result
import com.ssk.core.domain.SessionStorage
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

class HttpClientFactory(
    private val sessionStorage: SessionStorage
) {
    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
                level = LogLevel.ALL
                sanitizeHeader { header -> header == "Authorization" }
            }

            defaultRequest {
                this.contentType(ContentType.Application.Json)
                this.accept(ContentType.Application.Json)
                this.header("X-User-Email", Constants.SAM_EMAIL)
            }

            install(Auth) {
                this.bearer {
                    this.loadTokens {
                        BearerTokens(
                            accessToken = sessionStorage.getAccessToken() ?: "",
                            refreshToken = sessionStorage.getRefreshToken() ?: ""
                        )
                    }

                    this.refreshTokens {
                        val requestBearerTokens = this.client.post<AccessTokenRequest, AccessTokenResponse>(
                            route = constructRoute(Routes.TOKEN_REFRESH),
                            body = AccessTokenRequest(
                                refreshToken = sessionStorage.getRefreshToken() ?: ""
                            )
                        )
                        if (requestBearerTokens is Result.Success) {
                            sessionStorage.saveTokens(
                                requestBearerTokens.data.accessToken,
                                requestBearerTokens.data.refreshToken
                            )

                            BearerTokens(
                                accessToken = requestBearerTokens.data.accessToken,
                                refreshToken = requestBearerTokens.data.refreshToken
                            )
                        } else {
                            BearerTokens(
                                accessToken = "",
                                refreshToken = ""
                            )
                        }
                    }
                }
            }
        }
    }
}