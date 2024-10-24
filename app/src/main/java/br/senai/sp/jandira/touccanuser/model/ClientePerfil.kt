package br.senai.sp.jandira.touccanuser.model

data class ClientePerfil(
    var id: Int = 0,
    var nome_responsavel: String = "",
    var cpf_responsavel: String = "",
    var email: String = "",
    var nome_fanstasia: String = "",
    var razao_social: String = "",
    var telefone: String = "",
    var cnpj: String = "",
    var cep: String = "",
    var senha: String = "",
    var premium: Int = 0,
    var foto: String = ""
)
