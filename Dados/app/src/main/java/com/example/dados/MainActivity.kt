package com.example.dados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonD6 = findViewById<Button>(R.id.buttonD6)
        val buttonD12 = findViewById<Button>(R.id.buttonD12)
        val buttonD20 = findViewById<Button>(R.id.buttonD20)

        buttonD6.setOnClickListener {
            jogar(6)
        }
        buttonD12.setOnClickListener {
           jogarD12(12)
        }
        buttonD20.setOnClickListener {
            jogarD20(20)
        }

    }

    fun jogar(lados: Int) {

        val sortear = (1..lados).random()
        val textDado = findViewById<TextView>(R.id.textView)

        textDado.text = sortear.toString()




        /* val image = findViewById<ImageView>(R.id.imageView)

        when (sortear) {

            1 -> image.setImageResource(R.drawable.dice_1)
            2 -> image.setImageResource(R.drawable.dice_2)
            3 -> image.setImageResource(R.drawable.dice_3)
            4 -> image.setImageResource(R.drawable.dice_4)
            5 -> image.setImageResource(R.drawable.dice_5)
            6 -> image.setImageResource(R.drawable.dice_6)

        }
    }*/
    }

    fun jogarD12(lados12: Int){
        val sortearD12 = (1..lados12).random()
        val textDado12 = findViewById<TextView>(R.id.textView12)
        textDado12.text = sortearD12.toString()

    }
    fun jogarD20(lados20: Int){
        val sortearD20 = (1..lados20).random()
        val textDado20 = findViewById<TextView>(R.id.textView20)
        textDado20.text = sortearD20.toString()
    }
}