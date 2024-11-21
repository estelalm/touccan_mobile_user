package br.senai.sp.jandira.touccanuser.model

data class BicoHistorico(
    val id_bico: Int = 0,
    val bico: String = "",
    val descricao: String = "",
    val horario_inicio: String = "",
    val horario_limite: String = "",
    val data_inicio: String = "",
    val data_limite: String = "",
    val salario: Int = 0,
    val finalizado: Int = 0,
    val nome_cliente: String = "",
    val nome_usuario: String = ""
)