package es.imovil.fiestasasturias.rest

import es.imovil.fiestasasturias.model.Fiesta
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("fiestas")
    fun obtenerFiestas(): Call<List<Fiesta>>
}