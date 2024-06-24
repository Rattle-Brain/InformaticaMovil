package es.imovil.fiestasasturias.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.imovil.fiestasasturias.model.Fiestas
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.util.concurrent.TimeUnit

private val JSON_BASE_URL = "http://orion.edv.uniovi.es/"
private val HOME_URL = "http://192.168.8.102"
private val IMG_BASE_URL  = "https://www.turismoasturias.es"

interface RestApiService {
    @GET("FiestasDeInteres.json")

    suspend fun getFiestasFromLink(): Fiestas
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// Logging interceptor
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(HOME_URL)
    .client(client)
    .build()

object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}