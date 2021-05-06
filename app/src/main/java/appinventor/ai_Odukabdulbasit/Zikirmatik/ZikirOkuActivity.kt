package appinventor.ai_Odukabdulbasit.Zikirmatik

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ZikirOkuActivity : AppCompatActivity() {

    lateinit var zikirAdiTextView : TextView
    lateinit var zikirSayisiTextView: TextView
    lateinit var uyariLayout: LinearLayout

    lateinit var name : String
    var sayilacak :Int = 0


    val sharedPrefFile = "ZikirmatikZikirOkuma"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir_oku)

        title = "Zikirmatik"

        sharedPreferences = this?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()


        zikirAdiTextView = findViewById(R.id.zikirAdiTextView)
        zikirSayisiTextView = findViewById(R.id.zikirSayisiTextView)
        uyariLayout = findViewById(R.id.UyariLayout)

        name =intent.getStringExtra("name")
        sayilacak = sharedPreferences.getInt(name,0)

        zikirAdiTextView.text = name
        zikirSayisiTextView.text = sharedPreferences.getInt(name, 0).toString()

    }

    fun sayClicked(view: View){
        sayilacak ++

        save(sayilacak)
        load()
    }

    fun sifirlaClicked(view: View){

        //okunmakta olan zikir sifirlanacak
        ziroValue()
    }

    fun silClicked(view: View){

        uyariLayout.visibility = View.VISIBLE
    }

    fun evetClicked(view: View){

        //burada zikir tamamen silinecek
        val editor = sharedPreferences.edit()
        editor.remove(name)
        editor.commit()
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun  hayirClicked(view: View){

        uyariLayout.visibility = View.GONE
    }

    fun save(nameNumber: Int){

        val editor = sharedPreferences.edit()
        editor.putInt(name, nameNumber)
        editor.apply()
    }

    fun load() {

        var deger = sharedPreferences.getInt(name,0)
        zikirSayisiTextView.text = deger.toString()
        Log.i("info", deger.toString())
    }

    fun ziroValue(){

        sayilacak = 0
        val editor = sharedPreferences.edit()
        editor.putInt(name, 0)
        editor.apply()
        load()
    }


}
