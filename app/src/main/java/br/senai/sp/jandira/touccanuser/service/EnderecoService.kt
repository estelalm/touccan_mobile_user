package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Endereco
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    fun getViaCep(@Path("cep") cep: String): Call<Endereco>

}