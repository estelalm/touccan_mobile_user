package br.senai.sp.jandira.touccanuser.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResult(
    val usuario: UserId
)
