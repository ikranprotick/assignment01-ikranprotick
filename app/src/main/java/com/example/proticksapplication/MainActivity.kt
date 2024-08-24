package com.example.proticksapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
class MainActivity : AppCompatActivity() {
    private var hold=0
    private var score=0
    private var climbstart = false
    private var climbend=false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val scoreout = findViewById<TextView>(R.id.score)
        val climb = findViewById<Button>(R.id.climb)
        val reset = findViewById<Button>(R.id.reset)
        val fall = findViewById<Button>(R.id.fall)
        Log.d("start","Variable intialized")
        if(savedInstanceState!=null){
            score=savedInstanceState.getInt("score",0)
            hold =savedInstanceState.getInt("hold",0)
            climbend=savedInstanceState.getBoolean("climbend")
            climbstart=savedInstanceState.getBoolean("climbstart")

        }
        when(hold){
            in 1..3->{scoreout.setTextColor(Color.BLUE)}
            in 4..6->{scoreout.setTextColor(Color.GREEN)}
            in 7..9->{scoreout.setTextColor(Color.RED)}
        }
        scoreout.text="$score"
        reset.setOnClickListener{
            Log.d("reset","reset intialized")
            hold =0
            score = 0
            climbstart=false
            climbend=false
            scoreout.setTextColor(Color.BLACK)
            scoreout.text="$score"
        }
        climb.setOnClickListener{
            Log.d("climb","climb intialized")
            if(!climbend && score<18)
            {
                hold++
                climbstart = true
                when(hold){
                    in 0..3->{score++; scoreout.setTextColor(Color.BLUE)}
                    in 4..6->{score+=2;scoreout.setTextColor(Color.GREEN)}
                    in 7..9->{score+=3;scoreout.setTextColor(Color.RED)}
                }
                scoreout.text="$score"
                when(hold){
                    in 1..3->{scoreout.setTextColor(Color.BLUE)}
                    in 4..6->{scoreout.setTextColor(Color.GREEN)}
                    in 7..9->{scoreout.setTextColor(Color.RED)}
                }


            }
        }
        fall.setOnClickListener{
            Log.d("fall","fall intialized")
            if(climbstart){
                if(score!=18){
                    if(score<3){
                        score=0
                    }
                    else{
                        score-=3
                    }
                }
                climbend=true
                scoreout.text="$score"
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score",score)
        outState.putInt("hold",hold)
        outState.putBoolean("climbend",climbend)
        outState.putBoolean("climbstart",climbstart)

    }



}