//Created by Monica Ceisel

package com.example.www.mynewapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    private var colorOneName: String = ""
    private var colorTwoName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val colorTwo = findViewById<EditText>(R.id.editTextColor2)
        val next = findViewById<Button>(R.id.next)
        next.isEnabled = false
        //next.setBackgroundColor(Color.DKGRAY)

        if (intent != null && intent.extras != null) {
            this.colorOneName = intent.extras.getString("colorOneNameID")
            this.colorTwoName = intent.extras.getString("colorTwoNameID")
            if (this.colorTwoName.isNotBlank()) {
                colorTwo.setText(this.colorTwoName)
                next.isEnabled = true
                //next.setBackgroundColor(Color.LTGRAY)
            }
        }

        next.setOnClickListener {
            color(colorTwo)
            val intent = Intent(this@SecondActivity, LastActivity::class.java)
            intent.putExtra("colorOneNameID", this.colorOneName)
            intent.putExtra("colorTwoNameID", this.colorTwoName)
            startActivity(intent)
        }

        val prev = findViewById<Button>(R.id.prev)
        prev.setOnClickListener {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            intent.putExtra("colorOneNameID", this.colorOneName)
            intent.putExtra("colorTwoNameID", this.colorTwoName)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        colorTwo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                color(colorTwo)
                if (!verifyColor(colorTwoName)) {
                    next.isEnabled = false
                    //next.setBackgroundColor(Color.DKGRAY)
                    Toast.makeText(this, "$colorTwoName is invalid. Please enter a color.", Toast.LENGTH_SHORT).show()
                } else {
                    next.isEnabled = true
                    //next.setBackgroundColor(Color.LTGRAY)
                    Toast.makeText(this, "Thank you", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun color(colorTwo: EditText) {
        this.colorTwoName = colorTwo.text.toString()
        colorTwo.setText(this.colorTwoName)
    }

    private fun verifyColor(colorName: String): Boolean {
        var colorNameUpper = colorName.toUpperCase().trim()
        when (colorNameUpper) {
            "BLACK" -> return true
            "BLUE" -> return true
            "CYAN" -> return true
            "DARK GRAY" -> return true
            "DARK GREY" -> return true
            "GRAY" -> return true
            "GREY" -> return true
            "GREEN" -> return true
            "LIGHT GRAY" -> return true
            "LIGHT GREY" -> return true
            "MAGENTA" -> return true
            "RED" -> return true
            "YELLOW" -> return true
            "ORANGE" -> return true
            "PURPLE" -> return true
            "PINK" -> return true
            "WHITE" -> return true
            else -> {
                return false
            }
        }
    }
}
