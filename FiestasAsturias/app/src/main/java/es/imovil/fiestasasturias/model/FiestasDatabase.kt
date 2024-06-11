package es.imovil.fiestasasturias.model

import android.content.Context
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.imovil.fiestasasturias.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Fiesta::class], version = 1)
abstract class FiestasDatabase: RoomDatabase(){
    abstract fun dao(): FiestaDAO

    companion object {
        private var INSTANCE: FiestasDatabase? = null

        fun getInstance(context: Context): FiestasDatabase? {
            if (INSTANCE == null) {
                synchronized(FiestasDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FiestasDatabase::class.java,
                        "fiestas.db"
                    )
                        .addCallback(CALLBACK)
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroy() {
            INSTANCE = null
        }

        private val CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    // We try to obtain the list
                    try {
                        val fiestas = RestApi.retrofitService.getFiestasInfo()
                        fiestas.fiestas.map { INSTANCE!!.dao().insertFiesta(it) }

                    // If it fails we show a toast indicating so
                    } catch (_: Exception) {
                        Toast.makeText(null, "Fallo obteniendo una Fiesta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}