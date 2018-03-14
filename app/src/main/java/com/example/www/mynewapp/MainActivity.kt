//Created by Monica Ceisel

package com.example.www.mynewapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var colorOneName: String = ""
    private var colorTwoName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get view from activity_main.xml
        setContentView(R.layout.activity_main)

        val colorOne = findViewById<EditText>(R.id.editTextColor1)
        val button = findViewById<Button>(R.id.button)
        button.isEnabled = false

        if (intent != null && intent.extras != null) {
            this.colorOneName = intent.extras.getString("colorOneNameID")
            this.colorTwoName = intent.extras.getString("colorTwoNameID")
            if (this.colorOneName.isNotBlank()) {
                colorOne.setText(this.colorOneName)
                button.isEnabled = true

            }
        }

        button?.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("colorOneNameID", this.colorOneName)
            intent.putExtra("colorTwoNameID", this.colorTwoName)
            startActivity(intent)
        }

        colorOne.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                color(colorOne)
                if (!verifyColor(colorOneName)) {
                    button.isEnabled = false
                    Toast.makeText(this, "$colorOneName is invalid. Please enter a color.", Toast.LENGTH_SHORT).show()
                } else {
                    button.isEnabled = true
                    Toast.makeText(this, "Thank you", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnEditorActionListener false
        }
    }

    private fun color(colorOne: EditText) {
        this.colorOneName = colorOne.text.toString()
        colorOne.setText(colorOneName)
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
