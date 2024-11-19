package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.AvaliacaoUser
import br.senai.sp.jandira.touccanuser.model.FeedbackUser
import br.senai.sp.jandira.touccanuser.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface FeedbackService {

    @Headers("Content-Type: application/json")
    @POST("avaliacao/usuario")
    fun saveUser(@Body avaliacao: AvaliacaoUser): Call<AvaliacaoUser>

    @GET("feedback/usuario/{id}")
    fun getFeedbackUser(@Path("id") id: Int): Call<FeedbackUser>

}