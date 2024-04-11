package com.fiestasasturias.rest

import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.rest.ApiInterface
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService: ApiInterface {

    private const val BASE_URL = "https://ejemplo.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiInterface = retrofit.create(ApiInterface::class.java)
    override fun obtenerFiestas(): Call<List<Fiesta>> {
        TODO("Not yet implemented")
    }
}