package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Candidato
import br.senai.sp.jandira.touccanuser.model.UserCard
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CartaoService {

    @Headers("Content-Type: application/json")
    @POST("usuario/cartao")
    fun postCartao(@Body card: UserCard): Call<UserCard>
}