package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.ResultBico
import br.senai.sp.jandira.touccanuser.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BicoService {

    @GET("bico")
    fun getAllBicos(): Call<ResultBico>

}