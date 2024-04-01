package es.imovil.test_ingmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    val myButton2: Button by lazy {
        findViewById(R.id.button2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (savedInstanceState == null)
            Log.d(TAG, "La instancia es nula")
        else
            Log.d(TAG, "La instancia no es nula")
        Log.d(TAG, "Estoy en onCreate")
        myButton2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java )
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