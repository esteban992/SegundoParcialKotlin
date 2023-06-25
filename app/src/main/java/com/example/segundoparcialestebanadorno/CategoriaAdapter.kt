package com.example.segundoparcialestebanadorno

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoriaAdapter(val context: Context) : ListAdapter<Categoria, CategoriaAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Categoria) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val nombre : TextView = view.findViewById(R.id.textViewCategoria)
        //private val edad: TextView = view.findViewById(R.id.textViewDetailAge)
        private val imagenCategoria: ImageView = view.findViewById(R.id.imageViewCategoria)

        fun bind (categoria: Categoria) {
            nombre.text = categoria.nombre


            Glide.with(context)
                .load(categoria.imagen)
                .into(imagenCategoria)

            view.setOnClickListener {
                onItemClickListener(categoria)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaAdapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaAdapter.ViewHolder, position: Int) {
        val categoria = getItem(position)
        holder.bind(categoria)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Categoria>() {
        override fun areItemsTheSame(oldItem: Categoria, newItem: Categoria): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Categoria, newItem: Categoria): Boolean {
            return oldItem == newItem
        }
    }

}