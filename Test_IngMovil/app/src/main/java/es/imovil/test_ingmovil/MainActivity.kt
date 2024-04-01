package es.imovil.test_ingmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val TAG = "Actividad 1"

class MainActivity : AppCompatActivity() {
    val myButton: Button by lazy {
        findViewById(R.id.button)
    }

    val etTextoIngresado: EditText by lazy {
        findViewById(R.id.editTextText)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            Log.d(TAG, "La instancia es nula")
        else
            Log.d(TAG, "La instancia no es nula")
        Log.d(TAG, "Estoy en onCreate")

        myButton.setOnClickListener{

            val textoIngresado = etTextoIngresado.text.toString()
            Toast.makeText(this, "Texto ingresado: $textoIngresado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity2::class.java )
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Estoy en onStart")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "Estoy en onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Estoy en onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Estoy en onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Estoy en onPause")
        Thread.sleep(3000)

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Estoy en onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState == null)
            Log.d(TAG, "La instancia es nula. No hay que guardar nada")
        else
            Log.d(TAG, "La instancia no es nula. Guardando...")
        Log.d(TAG, "Estoy en onSaveInstanceState")
    }
}