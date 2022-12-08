package garcia.miguel.operaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import garcia.miguel.operaciones.databinding.ActivityMainBinding

private var flag: Boolean = true
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val OperacionesView: OperacionesViewModel by viewModels()
    //Listo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.revisar.setOnClickListener {view : View ->
            checkAnwer(view)
        }
        if(flag){
            OperacionesView.newRandom()
            flag = false
        }
        setValues()
    }
    private  fun setValues() {
        binding.operador1.text = OperacionesView.operando1.toString()
        binding.operador2.text = OperacionesView.operando2.toString()
        binding.operando.text = OperacionesView.operator
        binding.answer.setText("")
        binding.answer.setSelectAllOnFocus(true)
    }

    private fun checkAnwer(view: View){
        val answer = binding.answer.text
        val result:Int = when (OperacionesView.operator) {
            "+" -> {
                OperacionesView.operando1 + OperacionesView.operando2
            }
            "-" -> {
                OperacionesView.operando1 - OperacionesView.operando2
            }
            else -> {
                OperacionesView.operando1 * OperacionesView.operando2
            }
        }
        if(result.toString() == answer.toString()){
            val mySnack = Snackbar.make(view, R.string.correctToast, Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(getColor(R.color.green))
            mySnack.show()
            OperacionesView.newRandom()
            setValues()

        }
        else{
            val mySnack = Snackbar.make(view, R.string.incorrectToast, Snackbar.LENGTH_LONG)
            mySnack.setBackgroundTint(getColor(R.color.red))
            mySnack.show()
            binding.answer.setSelectAllOnFocus(true)
        }
    }
    //(minVal..maxVal).random()
    //private var caca :Int = (1..5).random()
}