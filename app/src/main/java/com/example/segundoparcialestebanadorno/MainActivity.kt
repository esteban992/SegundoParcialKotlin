package com.example.segundoparcialestebanadorno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.*
import okhttp3.Callback
import okhttp3.Call
import okhttp3.Request
import org.json.JSONObject
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCategorias:Button
    private lateinit var buttonRandom:Button
    private lateinit var progressBar : ProgressBar
    private lateinit var textViewJoke : TextView

    val URL = "https://api.chucknorris.io/jokes/random"
    var okHttpClient: OkHttpClient = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonCategorias = findViewById(R.id.buttonCategorias)
        buttonRandom = findViewById(R.id.buttonRandom)
        progressBar = findViewById(R.id.progressBar)
        textViewJoke = findViewById(R.id.textViewJoke)


        buttonRandom.setOnClickListener{
            loadRandomJoke()
        }


        buttonCategorias.setOnClickListener{
            val intent = Intent(this@MainActivity, MenuCategoria::class.java)

            startActivity(intent)
        }

    }

    private fun loadRandomJoke(){
        runOnUiThread{
            progressBar.visibility= View.VISIBLE
        }
        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object: Callback {


            override fun onFailure(call: Call?, e: IOException?) {
                showError()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()

                // obtenemos el chiste desde el servicio
                val txt = (JSONObject(json).get("value")).toString()

                //
                runOnUiThread{
                    progressBar.visibility = View.GONE
                    textViewJoke.text = txt
                }
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Fallo en la llamada", Toast.LENGTH_SHORT).show()
    }

}

