package br.senai.sp.jandira.touccanuser.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var nome: String = "",
    var email: String = "",
    var senha: String = "",
    var telefone: String = "",
    var cpf: String = "",
    var cep: String = "",
    var data_nascimento: String = ""
)