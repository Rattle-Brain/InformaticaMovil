package es.imovil.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import es.imovil.fragmentos.databinding.ActivityMainBinding

const val requestkey: String = "reqkey"
const val requestkey2: String = "reqkey2 "

class MainActivity : AppCompatActivity() {

    var swapped = false
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.camfrag.setOnClickListener {
            supportFragmentManager.commit {
                if(!swapped) {
                    replace(R.id.fragmentContainer2, FirstFragment())
                    replace(R.id.fragmentContainer1, SecondFragment())
                    swapped = true
                }
                else{
                    replace(R.id.fragmentContainer1, FirstFragment())
                    replace(R.id.fragmentContainer2, SecondFragment())
                    swapped = false
                }
            }
        }
    }
}