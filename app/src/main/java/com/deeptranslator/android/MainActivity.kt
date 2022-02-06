package com.deeptranslator.android

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.statusBarColor  = ContextCompat.getColor(this, R.color.colorCharacol)


        try{

            add_text.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if(p0!!.isEmpty()){
                        text_output.text = "Result"
                        return
                    }

                    val textFrom = p0.toString()

                    //val from = TranslateLanguage.fromLanguageTag(text_from.text.toString())!!
                    //val to = TranslateLanguage.fromLanguageTag(text_to.text.toString())!!

                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.ARABIC)
                        .build()

                    val way = Translation.getClient(options)



                    way.downloadModelIfNeeded().addOnSuccessListener {

                        way.translate(textFrom).addOnSuccessListener {
                            text_output.text = it
                        }.addOnFailureListener {
                            text_output.text = it.message
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })


        }catch(e:Exception){

        }

        text_from.setOnClickListener {
            val intent = Intent(this, LanguagesOfTranslator::class.java)
            startActivityForResult(intent, 12)
        }

    }


}