package com.example.jetpackconstraintlayout

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.fragment_note.view.*

class MyNoteRecyclerViewAdapter(
    private val mValues: List<NoteEntity>,
    private val context: Context
) : RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.noteEntity = item
        holder.txtTitle.text = holder.noteEntity.title
        holder.txtContent.text = holder.noteEntity.content

        holder.imgFav.setImageResource( if (holder.noteEntity.fav) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp )

        holder.imgFav.setOnClickListener {
            // TODO No implementado
        }

        holder.cardContainer.setCardBackgroundColor(ContextCompat.getColor(holder.mView.context, holder.noteEntity.color))
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var noteEntity: NoteEntity
        val cardContainer : CardView = mView.cardContainer
        val txtTitle = mView.txtTitle
        val txtContent = mView.txtContent
        val imgFav = mView.imgFav
    }
}