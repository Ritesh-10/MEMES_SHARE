package com.example.memes_share

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     var CurrImageUrl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
  private fun loadMeme()
  {
      progressBar.visibility=View.VISIBLE
      //val url="https://meme-api.herokuapp.com/gimme/NatureIsFuckingLit"
     //val url="https://serpapi.com/search.json?q=manchester+united&location=austin%2C+texas%2C+united+states&api_key=secret_api_key"//
//val url="https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=API_KEY"//
      val url = "https://meme-api.herokuapp.com/gimme"//

// Request a string response from the provided URL.
      val JsonObjectRequest= JsonObjectRequest(
          Request.Method.GET, url, null,
          Response.Listener{ response ->
         CurrImageUrl=response.getString("url")
          Glide.with(this).load(CurrImageUrl).listener(object:RequestListener<Drawable>{
              override fun onLoadFailed(
                  e: GlideException?,
                  model: Any?,
                  target: Target<Drawable>?,
                  isFirstResource: Boolean
              ): Boolean {
                  progressBar.visibility=View.GONE
                  return false
              }

              override fun onResourceReady(
                  resource: Drawable?,
                  model: Any?,
                  target: Target<Drawable>?,
                  dataSource: DataSource?,
                  isFirstResource: Boolean
              ): Boolean {
                 progressBar.visibility=View.GONE
                  return false
              }
          }).into(imageView)

          },
          Response.ErrorListener {
              Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
          })

// Add the request to the RequestQueue.
 MySingleton.getInstance(this).addToRequestQueue(JsonObjectRequest)
  }
    fun nextMeme(view: View) {
        loadMeme()
    }
    fun shareMeme(view: View) {
    val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"HEY CHECKOUT THIS MEME FROM REDDIT $CurrImageUrl")
        val chooser=Intent.createChooser(intent,"share this meme using....")
        startActivity(chooser)
    }

   //fun downMeme(view: View) {
       // val ivBackground = findViewById<ImageView>(R.id.imageView)
        //val btnSave = findViewById<Button>(R.id.btn_saveimage)
    }//
