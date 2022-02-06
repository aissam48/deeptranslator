package com.deeptranslator.android

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_languages_of_translator.*
import kotlinx.android.synthetic.main.layout_items.*

class LanguagesOfTranslator : AppCompatActivity() {


    lateinit var section: Section


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages_of_translator)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        _back.setOnClickListener {
            finish()
        }

        section = Section()
        val groupi = GroupAdapter<GroupieViewHolder>()
        groupi.add(section)
        recycler.adapter = groupi


        val allLanguages = TranslateLanguage.getAllLanguages()

        groupi.setOnItemClickListener { item, view ->

        }

        for (i in allLanguages){
            val symbole = TranslateLanguage.zza(i)

            val item = AdapterLanguage(symbole)

            section.add(item)
            groupi.notifyDataSetChanged()
        }


    }


    inner class AdapterLanguage (private val language:String):Item(){
        override fun bind(
            viewHolder: com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder,
            position: Int
        ) {

            viewHolder.text_language.text = language

        }

        override fun getLayout(): Int =R.layout.layout_items

    }
}

