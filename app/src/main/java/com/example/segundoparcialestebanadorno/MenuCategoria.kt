package com.example.segundoparcialestebanadorno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuCategoria : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menucategoria)

        recyclerView = findViewById(R.id.recyclerViewCategoria)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CategoriaAdapter(applicationContext)
        recyclerView.adapter = adapter

        adapter.submitList(getListadoCategoria())
        adapter.onItemClickListener = { categoria ->
            val intent = Intent(this, Detail::class.java)
            intent.putExtra("nombre", categoria.nombre)
            intent.putExtra("imagen", categoria.imagen)
            intent.putExtra("code", categoria.codigo)
            startActivity(intent)
        }

    }

    private fun getListadoCategoria(): MutableList<Categoria>? {

        val bundle = intent.extras

        val name = bundle?.getString("name","")
        //val code = bundle?.getString("code")


        return mutableListOf(
            Categoria(1,"Animal","https://i.kym-cdn.com/photos/images/original/000/097/728/chuck-norris-top-10-xl.jpg","animal"),
            Categoria(2,"Carrera","https://caknowledge.com/wp-content/uploads/2022/05/Chuck-Norris-Net-Worth-100-million.jpg","career" ),
            Categoria(3,"Celebridad","https://wwwflickeringmythc3c8f7.zapwp.com/q:i/r:0/wp:1/w:600/u:https://cdn.flickeringmyth.com/wp-content/uploads/2020/03/chuck-norris-600x389.jpg","celebrity" ),
            Categoria(4,"Desarrollador","http://codesqueeze.com/wp-content/2009/06/geek-chuck-norris-small.jpg","developer" ),
            Categoria(5,"Explícito","https://upload.wikimedia.org/wikipedia/commons/3/31/Chuck_Norris%2C_The_Delta_Force_1986_-_downsampled.jpg","explicit"),
            Categoria(6,"Fashion","https://hips.hearstapps.com/hmg-prod/images/chuck-norris-during-stunt-awards-news-photo-1684924462.jpg","fashion"),
            Categoria(7,"Comida","https://m.media-amazon.com/images/M/MV5BMjQwODU4Y2QtNDY1NS00NTVhLWI3OTctOGMzMDM1MzQ5OWViXkEyXkFqcGdeQXVyMDc2NTEzMw@@._V1_.jpg","food"),
            Categoria(8,"Historia","https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2018/12/chuck-norris-thanos.jpg","history"),
            Categoria(9,"Dinero","https://images2.bonanzastatic.com/afu/images/1984/3677/23/__57.jpg","money"),
            Categoria(10,"Películas","https://images02.military.com/sites/default/files/styles/full/public/2023-05/1time%20chuck%20norris%20water%201200.jpg","movie"),
            Categoria(11,"Música","https://i.pinimg.com/originals/04/4a/cb/044acb2633d91b59b3d2b984b2a4f695.jpg","music"),
            Categoria(12,"Política","https://m.media-amazon.com/images/I/81GQxaYzMyL.jpg","political"),
            Categoria(13,"Religión","https://i1.sndcdn.com/avatars-000038843240-q4k8or-t240x240.jpg","religion"),
            Categoria(14,"Ciencia","https://i.pinimg.com/736x/17/c8/ff/17c8ff58386c213f4e1d4bb0b3b676ef--science-memes-science-posters.jpg","science"),
            Categoria(15,"Deportes","https://as01.epimg.net/deporteyvida/imagenes/2019/06/11/portada/1560251185_999117_1560251411_noticia_normal.jpg","sport"),
            Categoria(16,"Viajes","https://imagecdn.handitv.com/a6FQ5-1636664080-790-blog-chuckpiece.jpg","travel"),
        )

    }
}