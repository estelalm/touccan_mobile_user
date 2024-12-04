package br.senai.sp.jandira.touccanuser.model

data class ChatMessage(
    val id_cliente: String? = null,
    val id_usuario: String? = null,
    val texto: String = "",
    val tipo: String = ""
)
