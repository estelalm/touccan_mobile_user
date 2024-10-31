package br.senai.sp.jandira.touccanuser.model

data class UserPerfil(
    val id: Int = 0,
    val nome: String = "",
    val cpf: String= "",
    val telefone: String = "",
    val cep: String = "",
    val email: String = "",
    val data_nascimento: String = "",
    val senha: String = "",
    val foto: String = "",
    val biografia: String = "",
    val habilidade: String = "",
    val id_formacao: Int = 0,
    val id_disponibilidade: Int = 0
)
