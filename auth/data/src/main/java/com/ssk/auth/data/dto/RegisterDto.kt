package com.ssk.auth.data.dto

import com.ssk.auth.domain.models.Register
import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val username: String,
    val email: String,
    val password: String,

)

fun RegisterDto.toDomain(): Register {
    return Register(
        username = username,
        email = email,
        password = password
    )
}

fun Register.toDto(): RegisterDto {
    return RegisterDto(
        username = username,
        email = email,
        password = password
    )
}
