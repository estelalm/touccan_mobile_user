package br.senai.sp.jandira.touccanuser.model

data class UserCard(
    val numero: Int = 0,
    val validade: String = "",
    val cvv: Int = 0,
    val nome_titular: String = "",
    val cpf: Long = 0,
    val apelido: String = "",
    val id_usuario: Int = 0
)
