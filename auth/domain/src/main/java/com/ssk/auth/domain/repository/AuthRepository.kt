package com.ssk.auth.domain.repository

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String,
        username: String
    ): EmptyResult<DataError.Network>

    suspend fun login(username: String, password: String): EmptyResult<DataError.Network>
}