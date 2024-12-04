package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.EmailReset
import br.senai.sp.jandira.touccanuser.model.Login
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.RelationRes
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.ResultUserProfileList
import br.senai.sp.jandira.touccanuser.model.TokenRes
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.model.UserPerfil
import br.senai.sp.jandira.touccanuser.model.UserSenha
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("usuario")
    fun saveUser(@Body user: User): Call<User>

    @Headers("Content-Type: application/json")
    @PUT("usuario/{id}")
    fun updateUser(@Body user: UserPerfil, @Path("id") id: Int): Call<UserPerfil>

    @Headers("Content-Type: application/json")
    @PUT("senha/usuario/{id}")
    fun updateUserSenha(@Body user: UserSenha, @Path("id") id: Int): Call<UserSenha>

    @Headers("Content-Type: application/json")
    @POST("login/usuario")
    fun loginUser(@Body user: Login): Call<LoginResult>

    @GET("usuario/{id}")
    fun getUserById(@Path("id") id: Int): Call<ResultUserProfile>

    @GET("usuario")
    fun getUsers(): Call<ResultUserProfileList>


    @GET("usuario/relacoes/{id}")
    fun getUserRelation(@Path("id") id: Int): Call<RelationRes>

    @Headers("Content-Type: application/json")
    @POST("enviar-token")
    fun sendToken(@Body email: EmailReset): Call<TokenRes>

}


