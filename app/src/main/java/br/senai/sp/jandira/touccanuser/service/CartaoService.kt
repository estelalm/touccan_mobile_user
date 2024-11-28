package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Candidato
import br.senai.sp.jandira.touccanuser.model.StripeApiResult
import br.senai.sp.jandira.touccanuser.model.UserCard
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface CartaoService {

    @Headers("Content-Type: application/json")
    @POST("usuario/cartao")
    fun postCartao(@Body card: UserCard): Call<UserCard>

    @Headers("Content-Type: application/json")
    @PUT("usuario/cartao")
    fun putCartao(@Body card: UserCard): Call<UserCard>

    @Headers("Content-Type: application/json")
    @POST("pagamento/usuario/conectar-usuario")
    fun getStripe(): Call<StripeApiResult>

}