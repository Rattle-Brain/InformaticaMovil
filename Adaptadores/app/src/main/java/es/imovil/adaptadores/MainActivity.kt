package es.imovil.adaptadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var spin: Spinner // Declaración del objeto Spinner
    private lateinit var etiqueta: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spin = findViewById(R.id.spinner)
        etiqueta = findViewById(R.id.label)
        val adaptador = ArrayAdapter(this, R.layout.spinner_cerrado, R.id.text, resources.getStringArray(R.array.animales))

        adaptador.setDropDownViewResource(R.layout.spinner_dropdown)
        spin.adapter = adaptador

        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion: TextView? = view?.findViewById(R.id.text)
                etiqueta.text = seleccion?.text
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}