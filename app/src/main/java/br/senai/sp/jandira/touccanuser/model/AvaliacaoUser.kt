package br.senai.sp.jandira.touccanuser.model

data class AvaliacaoUser(
    val id: Int = 0,
    val id_usuario: Int = 0,
    val id_cliente: Int = 0,
    val id_bico: Int = 0,
    val avaliacao: String = "",
    val nota: Int = 0
)
