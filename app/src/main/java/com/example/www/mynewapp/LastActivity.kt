//Created by Monica Ceisel

package com.example.www.mynewapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_last.*

class LastActivity : AppCompatActivity() {
    private var colorOneName: String = ""
    private var colorTwoName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        if (intent != null && intent.extras != null) {
            this.colorOneName = intent.extras.getString("colorOneNameID")
            this.colorTwoName = intent.extras.getString("colorTwoNameID")
        }

        //capture button click
        val done = findViewById<Button>(R.id.done)
        done.setOnClickListener {
            val intent = Intent(this@LastActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val prev = findViewById<Button>(R.id.prev)
        prev.setOnClickListener {
            val intent = Intent(this@LastActivity, SecondActivity::class.java)
            intent.putExtra("colorOneNameID", this.colorOneName)
            intent.putExtra("colorTwoNameID", this.colorTwoName)
            startActivity(intent)
        }

        //https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        Log.e("+++++++++++++++++++", this.colorOneName)


        val color1: TextView = findViewById(R.id.display1)
        color1.text = this.colorOneName
        Log.e("++++++++++++++++++", "COLOR1" + color1.text)


        val color2: TextView = findViewById(R.id.display2)
        color2.text = this.colorTwoName
        Log.e("++++++++++++++++++", "COLOR 2" + color2.text)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        color1.setTextColor(selectColor(this.colorTwoName))
        color2.setTextColor(selectColor(this.colorOneName))
        topcolor.setBackgroundColor(selectColor(this.colorOneName))
        bottomcolor.setBackgroundColor(selectColor(this.colorTwoName))
    }

    private fun selectColor(colorName: String): Int {
        var colorNameUpper = colorName.toUpperCase().trim()
        when (colorNameUpper) {
            "BLACK" -> return Color.BLACK
            "BLUE" -> return Color.BLUE
            "CYAN" -> return Color.CYAN
            "DARK GRAY" -> return Color.DKGRAY
            "DARK GREY" -> return Color.DKGRAY
            "GRAY" -> return Color.GRAY
            "GREY" -> return Color.GRAY
            "GREEN" -> return Color.GREEN
            "LIGHT GRAY" -> return Color.LTGRAY
            "LIGHT GREY" -> return Color.LTGRAY
            "MAGENTA" -> return Color.MAGENTA
            "RED" -> return Color.RED
            "YELLOW" -> return Color.YELLOW
            "ORANGE" -> return Color.rgb(255,165,0)
            "PURPLE" -> return Color.rgb(128, 0, 128)
            "PINK" -> return Color.rgb(255,192,203)
            else -> {
                return Color.WHITE
            }
        }
    }
}
