package com.ssk.auth.domain.repository

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String,
        username: String
    ): EmptyResult<DataError.Network>

    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>

    suspend fun saveUsername(username: String)

    suspend fun getUsername(): String
    
    suspend fun isLoggedIn(): Boolean
}