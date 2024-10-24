package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.ResultBicos
import retrofit2.Call
import retrofit2.http.GET

interface BicoService {

    @GET("bico")
    fun getAllBicos(): Call<ResultBicos>

}