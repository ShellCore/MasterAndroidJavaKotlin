package com.example.jetpackconstraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NoteFragment : Fragment() {

    lateinit var noteEntities : List<NoteEntity>
    lateinit var recAdapter : MyNoteRecyclerViewAdapter

    // TODO: Customize parameters
    private var columnCount = 2

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

                noteEntities = listOf(
                    NoteEntity("Lista de compra", "Comprar pan tostado", false, android.R.color.holo_blue_light),
                    NoteEntity("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", true, android.R.color.holo_green_light),
                    NoteEntity("Cumpleaños (fiesta)", "No olvidar las velas", false, android.R.color.holo_red_light)
                )

                recAdapter = MyNoteRecyclerViewAdapter(noteEntities, context)
                adapter = recAdapter
            }
        }
        return view
    }
}
