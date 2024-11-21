package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.AceitosResult
import br.senai.sp.jandira.touccanuser.model.Candidato
import br.senai.sp.jandira.touccanuser.model.ClienteId
import br.senai.sp.jandira.touccanuser.model.Contratado
import br.senai.sp.jandira.touccanuser.model.Finalizar
import br.senai.sp.jandira.touccanuser.model.FinalizarCliente
import br.senai.sp.jandira.touccanuser.model.HistoryResult
import br.senai.sp.jandira.touccanuser.model.Login
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.model.ResultBicos
import br.senai.sp.jandira.touccanuser.model.ResultBicosPremium
import br.senai.sp.jandira.touccanuser.model.ResultCandidatos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BicoService {

    @GET("bico")
    fun getAllBicos(): Call<ResultBicos>

    @GET("bico/premium")
    fun getUrgente(): Call<ResultBicos>

    @GET("bico/cep/{cep}")
    fun getBicoByCep(@Path("cep") cep:String): Call<ResultBicos>

    @GET("bico/{id}")
    fun getBicoById(@Path("id") id:Int): Call<ResultBico>

    @Headers("Content-Type: application/json")
    @POST("candidato")
    fun postCandidato(@Body user: Candidato): Call<Candidato>

    @GET("candidato/{id}")
    fun getCandidatosByBico(@Path("id") id:Int): Call<ResultCandidatos>

    @GET("bico/premium")
    fun getBicosPremium(): Call<ResultBicosPremium>

    @GET("bico/candidato/{id}")
    fun getBicoByUsuario(@Path("id") id: Int): Call<HistoryResult>

    @GET("bico/canditados/contratados/{id}")
    fun getContratadosByBico(@Path("id") id: Int): Call<AceitosResult>

    @Headers("Content-Type: application/json")
    @POST("bico")
    fun getBicosByCLiente(@Body cliente: ClienteId): Call<ResultBicosPremium>

    @Headers("Content-Type: application/json")
    @POST("finalizar/usuario")
    fun finalizarUsuario(@Body finalizar: Finalizar): Call<Finalizar>

    @Headers("Content-Type: application/json")
    @POST("finalizar/cliente")
    fun finalizarCliente(@Body finalizar: FinalizarCliente): Call<FinalizarCliente>

}