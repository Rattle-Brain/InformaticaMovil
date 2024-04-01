package es.imovil.a01tanteo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import es.imovil.a01tanteo.databinding.ActivityMainBinding


const val PLOCAL = "LOCAL"
const val PVISITANTE = "VISITANTE"

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    val tanteoVM: TanteoVM by lazy{
        ViewModelProvider(this).get(TanteoVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setLabelsText()
        binding.local3.setOnClickListener{
            tanteoVM.pointsLocal.incThreePointer()
            binding.pointsLocal.text = tanteoVM.pointsLocal.total.toString()
            binding.n3loc.text = tanteoVM.pointsLocal.threePointer.toString()
        }
        binding.local2.setOnClickListener{
            tanteoVM.pointsLocal.incTwoPointer()
            binding.pointsLocal.text = tanteoVM.pointsLocal.total.toString()
            binding.n2loc.text = tanteoVM.pointsLocal.twoPointer.toString()
        }
        binding.local1.setOnClickListener{
            tanteoVM.pointsLocal.incOnePointer()
            binding.pointsLocal.text = tanteoVM.pointsLocal.total.toString()
            binding.n1loc.text = tanteoVM.pointsLocal.onePointer.toString()
        }

        binding.visitor3.setOnClickListener{
            tanteoVM.pointsVisitor.incThreePointer()
            binding.pointsVisitor.text = tanteoVM.pointsVisitor.total.toString()
            binding.n3vis.text = tanteoVM.pointsVisitor.threePointer.toString()
        }
        binding.visitor2.setOnClickListener{
            tanteoVM.pointsVisitor.incTwoPointer()
            binding.pointsVisitor.text = tanteoVM.pointsVisitor.total.toString()
            binding.n2vis.text = tanteoVM.pointsVisitor.twoPointer.toString()
        }
        binding.visitor1.setOnClickListener{
            tanteoVM.pointsVisitor.incOnePointer()
            binding.pointsVisitor.text = tanteoVM.pointsVisitor.total.toString()
            binding.n1vis.text = tanteoVM.pointsVisitor.onePointer.toString()
        }

        binding.estadisticas.setOnClickListener{
            Intent(this, MainActivity2::class.java).apply {
                putExtra(PLOCAL, tanteoVM.pointsLocal)
                putExtra(PVISITANTE, tanteoVM.pointsVisitor)
                startActivity(this)
            }
        }
    }

    private fun setLabelsText() {
        binding.n3loc.text = tanteoVM.pointsLocal.threePointer.toString()
        binding.n2loc.text = tanteoVM.pointsLocal.twoPointer.toString()
        binding.n1loc.text = tanteoVM.pointsLocal.onePointer.toString()
        binding.pointsLocal.text = tanteoVM.pointsLocal.calcTotal().toString()

        binding.n3vis.text = tanteoVM.pointsVisitor.threePointer.toString()
        binding.n2vis.text = tanteoVM.pointsVisitor.twoPointer.toString()
        binding.n1vis.text = tanteoVM.pointsVisitor.onePointer.toString()
        binding.pointsVisitor.text = tanteoVM.pointsVisitor.calcTotal().toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "Estoy en onStart")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("TAG", "Estoy en onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "Estoy en onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "Estoy en onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "Estoy en onPause")

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "Estoy en onResume")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}