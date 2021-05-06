package appinventor.ai_Odukabdulbasit.Zikirmatik

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.R.attr.key
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.w3c.dom.Text
import java.io.Serializable
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var ekleButton : Button
    lateinit var zikirList : ListView

    lateinit var arrayAdapter : rowAdapter

    var ZikirlerArrayList : ArrayList<String> = ArrayList()

    val sharedPrefFile = "ZikirmatikZikirOkuma"
    lateinit var editor : SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Zikirlerim"
        val sharedPraferences = this?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        editor = sharedPraferences.edit()

        editText = findViewById(R.id.editText)
        ekleButton = findViewById(R.id.ekleButton)
        zikirList = findViewById(R.id.zikirList)



        //sharpraferencesten bilgileri arrayliste yukleme yeri
        val zikirSayiArrayList = sharedPraferences.all.keys

        for (i in zikirSayiArrayList){

            ZikirlerArrayList.add(i)
        }

       arrayAdapter = rowAdapter(this, ZikirlerArrayList)
       zikirList.adapter = arrayAdapter


        zikirList.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, ZikirOkuActivity ::class.java)
            intent.putExtra("name", ZikirlerArrayList[position])
            startActivity(intent)
        }

        ekleButton.setOnClickListener(View.OnClickListener {

            if (editText.text.toString() != ""){
                ZikirlerArrayList.add(editText.text.toString())

                hideKeyBoard()
                arrayAdapter.notifyDataSetChanged()

                editor.putInt(editText.text.toString(), 0)
                editor.apply()

                editText.text.clear()
            }
        })
    }

    fun hideKeyBoard(){
        val view = this.currentFocus
        if (view != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}
