package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.ClientePerfil
import br.senai.sp.jandira.touccanuser.model.ResultClientProfile
import br.senai.sp.jandira.touccanuser.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {
    @GET("cliente/{id}")
    fun getClientById(@Path("id") id: Int): Call<ResultClientProfile>
}