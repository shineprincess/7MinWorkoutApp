package com.shineprincess.workoutappp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llStart.setOnClickListener {
           val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        llBMI_Calculator.setOnClickListener {
            val intent = Intent(this,BmiActivity::class.java)
            startActivity(intent)
        }

        llBMI_History.setOnClickListener {
            val intent = Intent(this,ExerciseHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}