package ipca.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var operand = 0.0
    var operator = ""
    var userIsInTheMiddleOfOperation = false
    var hasDot = false

    var displayFun : ((view:View)->Unit) = { view : View ->
        var button = (view as Button)
        if (userIsInTheMiddleOfOperation){
            if(textViewDisplay.text.equals("0")&&!button.text.equals(".")){
                textViewDisplay.text=""
            }
            if(button.text.equals(".")){
                if(!hasDot) {
                    textViewDisplay.text = "${textViewDisplay.text}."
                    hasDot = true
                }
            }else {
                textViewDisplay.text = "${textViewDisplay.text}${button.text}"
            }
        }else {
            textViewDisplay.text = "${button.text}"
            userIsInTheMiddleOfOperation = true
        }

    }

    var operationFun : ((view:View)->Unit) = { view : View ->

        if (operator.equals("")) {
            operand = textViewDisplay.text.toString().toDouble()
            operator = (view as Button).text.toString()
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }else {
            var secondOperand = textViewDisplay.text.toString().toDouble()
            var result = doOperation(operand,secondOperand, operator)
            operator = (view as Button).text.toString()
            operand = result
            textViewDisplay.text = "${result}"
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }

    }

    fun doOperation(op1:Double,op2:Double,operator:String) : Double {
        var result = 0.0
        when(operator){
            "+" -> {
                result = op1 + op2
            }
            "-" -> {
                result = op1 - op2
            }
            "*" -> {
                result = op1 * op2
            }
            "/" -> {
                result = op1 / op2
            }
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonN9.setOnClickListener(displayFun)
        buttonN8.setOnClickListener(displayFun)
        buttonN7.setOnClickListener(displayFun)
        buttonN6.setOnClickListener(displayFun)
        buttonN5.setOnClickListener(displayFun)
        buttonN4.setOnClickListener(displayFun)
        buttonN3.setOnClickListener(displayFun)
        buttonN2.setOnClickListener(displayFun)
        buttonN1.setOnClickListener(displayFun)
        buttonN0.setOnClickListener(displayFun)
        buttonDot.setOnClickListener(displayFun)

        buttonOpPlus.setOnClickListener(operationFun)
        buttonOpMinus.setOnClickListener(operationFun)
        buttonOpTimes.setOnClickListener(operationFun)
        buttonOpDivision.setOnClickListener(operationFun)

        buttonOpEqual.setOnClickListener{
            var secondOperand = textViewDisplay.text.toString().toDouble()
            var result = doOperation(operand,secondOperand, operator)
            textViewDisplay.text = "${result}"
            userIsInTheMiddleOfOperation = false
        }

        buttonClear.setOnClickListener{
            textViewDisplay.text = "0"
            operand = 0.0
            operator = ""
            userIsInTheMiddleOfOperation = false
            hasDot = false
        }

    }
}
