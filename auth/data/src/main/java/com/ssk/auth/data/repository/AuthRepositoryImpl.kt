package com.ssk.auth.data.repository

import com.ssk.auth.data.DTO.RegisterDto
import com.ssk.auth.domain.repository.AuthRepository
import com.ssk.core.data.network.post
import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String,
        username: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterDto, Unit>(
            route = "/register",
            body = RegisterDto(
                email = email,
                password = password,
                username = username
            )
        )
    }

    override suspend fun login(
        username: String,
        password: String
    ): EmptyResult<DataError.Network> {
        TODO("Not yet implemented")
    }

}