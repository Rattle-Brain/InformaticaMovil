package es.imovil.fiestasasturias.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.imovil.fiestasasturias.model.Fiestas
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private val JSON_BASE_URL = "http://orion.edv.uniovi.es/~arias/json/"

/**
 * Servicio API Rest para poder obtener las fiestas a través de internet
 */
interface RestApiService {
    @GET("FiestasDeInteres.json")
    suspend fun getFiestasFromLink(): Fiestas
}
// Creamos el moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Logger que permite ver si la consulta llega corectamente
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

// Cliente que utiliza el logger anterior
val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()

// Creamos retrofit con el cliente para poder hacer logs de la información
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(JSON_BASE_URL)
    .client(client)
    .build()

/**
 * Api Rest
 */
object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}