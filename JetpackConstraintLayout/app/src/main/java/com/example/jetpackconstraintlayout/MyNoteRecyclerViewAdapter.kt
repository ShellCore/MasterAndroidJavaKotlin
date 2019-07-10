package com.example.jetpackconstraintlayout

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import kotlinx.android.synthetic.main.fragment_note.view.*

class MyNoteRecyclerViewAdapter(
    private val mValues: List<Note>,
    private val mListener: NotesInteractionListener?
) : RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.note = item
        holder.txtTitle.text = holder.note.title
        holder.txtContent.text = holder.note.content

        holder.imgFav.setImageResource( if (holder.note.fav) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp )

        holder.imgFav.setOnClickListener {
            mListener?.favNoteClick(holder.note)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var note: Note
        val txtTitle = mView.txtTitle
        val txtContent = mView.txtContent
        val imgFav = mView.imgFav
    }
}
