package com.example.doodlz

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doodleView: DoodleView = findViewById(R.id.doodleView)
        val changeColorButton: Button = findViewById(R.id.changeColorButton)

        changeColorButton.setOnClickListener {
            doodleView.changeColor()
        }

    }

}

