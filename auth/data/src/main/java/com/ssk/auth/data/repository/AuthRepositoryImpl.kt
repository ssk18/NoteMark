package com.ssk.auth.data.repository

import com.ssk.auth.data.dto.LoginRequest
import com.ssk.auth.data.dto.LoginResponse
import com.ssk.auth.data.dto.RegisterDto
import com.ssk.auth.domain.repository.AuthRepository
import com.ssk.core.data.Routes
import com.ssk.core.data.network.post
import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import com.ssk.core.domain.Result
import com.ssk.core.domain.SessionStorage
import com.ssk.core.domain.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String,
        username: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterDto, Unit>(
            route = Routes.REGISTATION,
            body = RegisterDto(
                email = email,
                password = password,
                username = username
            )
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result =  httpClient.post<LoginRequest, LoginResponse>(
            route = Routes.LOGIN,
            body = LoginRequest(
                email = email,
                password = password
            )
        )
        if (result is Result.Success) {
            sessionStorage.saveTokens(
                accessToken = result.data.accessToken,
                refreshToken = result.data.refreshToken
            )
        }
        return result.asEmptyDataResult()
    }

}