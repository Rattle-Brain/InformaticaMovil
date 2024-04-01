package es.imovil.a01tanteo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.imovil.a01tanteo.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var localTeam: Puntos
    lateinit var visitTeam: Puntos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        localTeam = intent.getParcelableExtra(PLOCAL) ?: Puntos()
        visitTeam = intent.getParcelableExtra(PVISITANTE) ?: Puntos()

        updateUI(localTeam, visitTeam)

        binding.localBar1.isIndeterminate = false
        System.out.println(localTeam.onePointer.toString() + "  " + "  " + localTeam.nTirosTotal().toString())
        binding.localBar1.progress = (localTeam.onePointer / localTeam.nTirosTotal()) * 100
    }

    private fun updateUI(localTeam: Puntos, visitTeam: Puntos) {
        val puntosLocal = localTeam.calcTotal()
        val puntosVis = visitTeam.calcTotal()

    }
}