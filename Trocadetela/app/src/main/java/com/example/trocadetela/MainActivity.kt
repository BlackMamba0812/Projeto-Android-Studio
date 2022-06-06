package com.example.trocadetela

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo","onStart()")

        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val intentSegunda = Intent(this, SegundaTela::class.java)


        buttonNext.setOnClickListener {
            startActivity(intentSegunda)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ciclo", "onRestart")
    }
}