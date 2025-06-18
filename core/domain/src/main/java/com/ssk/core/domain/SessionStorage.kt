package com.ssk.core.domain

interface SessionStorage {
    suspend fun getAccessToken(): String?
    suspend fun saveAccessToken(token: String)
    suspend fun getRefreshToken(): String?
    suspend fun saveRefreshToken(token: String)
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun saveUsername(username: String)
    suspend fun getUsername(): String
    suspend fun clearTokens()
}