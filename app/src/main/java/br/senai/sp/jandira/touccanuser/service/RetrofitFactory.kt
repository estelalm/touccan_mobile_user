package br.senai.sp.jandira.touccanuser.service

import br.senai.sp.jandira.touccanuser.model.Endereco
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://touccan-backend-8a78.onrender.com/2.0/touccan/"

//    private val BASE_URL = "http://10.0.2.2:8080/2.0/tocuccan/"

    //cliente http
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserService(): UserService {
        return retrofitFactory.create(UserService::class.java)
    }

    fun getBicoService(): BicoService {
        return retrofitFactory.create(BicoService::class.java)
    }

    fun getClientService(): ClientService{
        return retrofitFactory.create(ClientService::class.java)
    }

    fun getCartaoService(): CartaoService{
        return retrofitFactory.create(CartaoService::class.java)
    }

    fun getFeedbackService(): FeedbackService{
        return retrofitFactory.create(FeedbackService::class.java)
    }

    private val retrofitViaCep = Retrofit
        .Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getEnderecoService(): EnderecoService{
        return retrofitViaCep.create(EnderecoService::class.java)
    }

}