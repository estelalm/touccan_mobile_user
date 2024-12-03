package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.AvaliacaoRes
import br.senai.sp.jandira.touccanuser.model.AvaliacaoUser
import br.senai.sp.jandira.touccanuser.model.DenunciaUser
import br.senai.sp.jandira.touccanuser.model.DenunciaRes
import br.senai.sp.jandira.touccanuser.model.EmailTouccan
import br.senai.sp.jandira.touccanuser.model.FeedbackUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface FeedbackService {

    @Headers("Content-Type: application/json")
    @POST("avaliacao/usuario")
    fun saveUserRate(@Body avaliacao: AvaliacaoUser): Call<AvaliacaoRes>

    @Headers("Content-Type: application/json")
    @POST("denuncia/usuario")
    fun saveUserDenuncia(@Body denuncia: DenunciaUser): Call<DenunciaRes>

    @GET("feedback/usuario/{id}")
    fun getFeedbackUser(@Path("id") id: Int): Call<FeedbackUser>

    @GET("feedback/cliente/{id}")
    fun getFeedbackClient(@Path("id") id: Int): Call<FeedbackUser>

    @Headers("Content-Type: application/json")
    @POST("enviar-email")
    fun postEmailReport(@Body email: EmailTouccan): Call<EmailTouccan>

}