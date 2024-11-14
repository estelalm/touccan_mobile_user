package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Login
import br.senai.sp.jandira.touccanuser.model.LoginResult
import br.senai.sp.jandira.touccanuser.model.ResultUserProfile
import br.senai.sp.jandira.touccanuser.model.User
import br.senai.sp.jandira.touccanuser.model.UserPerfil
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
    @POST("login/usuario")
    fun loginUser(@Body user: Login): Call<LoginResult>

    @GET("usuario/{id}")
    fun getUserById(@Path("id") id: Int): Call<ResultUserProfile>


}


