package br.senai.sp.jandira.touccanuser.model

import com.google.type.DateTime

data class Bico(
    val id :Int = 0,
    val titulo: String = "",
    val descricao: String = "",
    val horario_inicio: String = "",
    val data_inicio: String = "",
    val horario_limite: String = "",
    val data_limite: String = "",
    val salario: Number = 0,
    val finalizado: Int = 0,
    val final_c: Int = 0,
    val final_u: Int = 0,
    val categoria: List<Categoria> = listOf(),
    val dificuldade: List<Dificuldade> = listOf(),
    val cliente: List<Cliente> = listOf(),
    val id_endereco:List<Endereco> = listOf()
)


