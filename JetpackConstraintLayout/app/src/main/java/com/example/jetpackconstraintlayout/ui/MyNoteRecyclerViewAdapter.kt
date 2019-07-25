package com.example.jetpackconstraintlayout.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackconstraintlayout.R
import com.example.jetpackconstraintlayout.db.entity.NoteEntity
import kotlinx.android.synthetic.main.fragment_note.view.*

class MyNoteRecyclerViewAdapter(
    private var mValues: List<NoteEntity>,
    private val context: Context,
    private val viewModel: NewNoteDialogViewModel = ViewModelProviders.of(context as AppCompatActivity).get(
        NewNoteDialogViewModel::class.java
    )
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

        holder.imgFav.setImageResource(if (holder.noteEntity.fav) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)

        holder.imgFav.setOnClickListener {
            holder.noteEntity.fav = !holder.noteEntity.fav
            holder.imgFav.setImageResource(if (holder.noteEntity.fav) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)
            viewModel.updatetNote(holder.noteEntity)
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun setNewNotes(newNotes: List<NoteEntity>) {
        mValues = newNotes
        notifyDataSetChanged()
    }

    inner class ViewHolder constructor(val mView: View) : RecyclerView.ViewHolder(mView) {

        lateinit var noteEntity: NoteEntity
        val cardContainer: CardView = mView.cardContainer
        val txtTitle: TextView = mView.txtTitle
        val txtContent: TextView = mView.txtContent
        val imgFav: ImageView = mView.imgFav

    }
}
