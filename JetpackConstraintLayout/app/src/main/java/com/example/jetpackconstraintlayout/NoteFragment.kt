package com.example.jetpackconstraintlayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NoteFragment : Fragment() {

    lateinit var notes : List<Note>
    lateinit var recAdapter : MyNoteRecyclerViewAdapter

    // TODO: Customize parameters
    private var columnCount = 2

    private var listener: NotesInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    view.id == R.id.listPotrait -> LinearLayoutManager(context)
                    else -> {
                        val displayMetrics = context.resources.displayMetrics
                        val dpWidth : Float = displayMetrics.widthPixels / displayMetrics.density
                        val columnNum = (dpWidth / 180).toInt()

                        StaggeredGridLayoutManager(columnNum, StaggeredGridLayoutManager.VERTICAL)
                    }

                }

                notes = listOf(
                    Note("Lista de compra", "Comprar pan tostado", false, android.R.color.holo_blue_light),
                    Note("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", true, android.R.color.holo_green_light),
                    Note("Cumpleaños (fiesta)", "No olvidar las velas", false, android.R.color.holo_red_light)
                )

                recAdapter = MyNoteRecyclerViewAdapter(notes, listener)
                adapter = recAdapter
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NotesInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


}
