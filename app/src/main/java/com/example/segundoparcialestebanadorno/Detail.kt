package com.example.segundoparcialestebanadorno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Detail : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewChiste: TextView
    private lateinit var imageView: ImageView
    private lateinit var buttonCategoryJoke: Button

    val URL = "https://api.chucknorris.io/jokes/random?category="
    var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textViewName = findViewById(R.id.textViewName)
        textViewChiste = findViewById(R.id.textViewChiste)
        imageView = findViewById(R.id.imageViewDetalle)
        buttonCategoryJoke = findViewById(R.id.buttonCategoryJoke)



        val bundle = intent.extras
        val nombre = bundle?.getString("nombre", "")
        val imagen = bundle?.getString("imagen") ?: ""
        val code  = bundle?.getString("code", "")

        buttonCategoryJoke.setOnClickListener{loadCategoryJoke(code)}

        textViewName.text = nombre
        Glide.with(applicationContext)
            .load(imagen)
            .into(imageView)


    }


    private fun loadCategoryJoke(codigo:String?){
        runOnUiThread{

        }
        val request: Request = Request.Builder().url(URL + codigo).build()
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
                    textViewChiste.text = txt
                }
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Fallo en la llamada", Toast.LENGTH_SHORT).show()
    }


}