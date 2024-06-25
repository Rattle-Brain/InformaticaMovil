package es.imovil.fiestasasturias

import android.app.Application
import es.imovil.fiestasasturias.data.FiestaRepository
import es.imovil.fiestasasturias.model.FiestasDatabase

//val Context.dataStore by preferencesDataStore(name="settings")
class App: Application() {

    val fiestadb by lazy {
        FiestasDatabase.getInstance(this)
    }
    val fiestaRepo by lazy {
        FiestaRepository(fiestadb.dao())
    }
}