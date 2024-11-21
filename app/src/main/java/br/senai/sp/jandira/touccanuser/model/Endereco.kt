package br.senai.sp.jandira.touccanuser.model

data class Endereco(
    val id: Int = 0,
    val rua: String = "",
    val cidade: String = "",
    val bairro: String = "",
    val estado: String = "",
)