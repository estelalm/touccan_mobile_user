package br.senai.sp.jandira.touccanuser.model

import com.google.type.DateTime

data class BicoPremium(
    val id: Int = 0,
    val titulo: String = "",
    val descricao: String = "",
    val horario_inicio: String = "",
    val data_inicio: String = "",
    val horario_limite: String = "",
    val data_limite: String = "",
    val salario: Int = 0,
    val finalizado: Int = 0,
    val id_dificuldade: Int = 0,
    val id_categoria: Int = 0,
    val id_cliente: Int = 0,
    val final_c: Int = 0,
    val final_u: Int = 0,
    val nome_responsavel: String = "",
    val cpf_responsavel: String = "",
    val email: String = "",
    val nome_fantasia: String = "",
    val razao_social: String = "",
    val telefone: String = "",
    val cnpj: String = "",
    val cep: String = "",
    val senha: String = "",
    val premium: Int = 0,
    val foto: String = ""
)
//"id_dificuldade": 1,
//"id_categoria": 1,
//"id_cliente": 1

