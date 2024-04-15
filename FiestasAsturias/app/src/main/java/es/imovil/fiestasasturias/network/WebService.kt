package es.imovil.fiestasasturias.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.imovil.fiestasasturias.model.Fiestatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val JSON_BASE_URL = "http://orion.edv.uniovi.es/~arias/json/FiestasDeInteres.json"
private val IMG_BASE_URL  = "https://www.turismoasturias.es"

interface RestApiService {
    @GET("descargar.php?id=935&tipo=JSON")
    suspend fun getStatusInfo(): Fiestatus
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(JSON_BASE_URL)
    .build()

object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}