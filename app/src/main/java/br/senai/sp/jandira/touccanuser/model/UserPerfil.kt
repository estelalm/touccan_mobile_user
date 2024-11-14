package br.senai.sp.jandira.touccanuser.model

data class UserPerfil(
    val id: Int = 0,
    var nome: String = "",
    val cpf: String= "",
    val telefone: String = "",
    val cep: String = "",
    val email: String = "",
    var data_nascimento: String = "",
    val senha: String = "",
    var foto: String = "",
    var biografia: String = "",
    var habilidade: String = "",
    var id_formacao: Int = 0,
    var id_disponibilidade: Int = 0
)
