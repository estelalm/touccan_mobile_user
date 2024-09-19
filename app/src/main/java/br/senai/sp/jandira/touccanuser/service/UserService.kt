package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Login
import br.senai.sp.jandira.touccanuser.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("usuario")
    fun saveUser(@Body user: User): Call<User>


    @Headers("Content-Type: application/json")
    @POST("login/usuario")
    fun loginUser(@Body user: Login): Call<Login>
}