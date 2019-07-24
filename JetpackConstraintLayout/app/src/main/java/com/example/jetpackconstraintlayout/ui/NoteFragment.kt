package com.example.jetpackconstraintlayout.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpackconstraintlayout.R
import com.example.jetpackconstraintlayout.db.entity.NoteEntity

class NoteFragment : Fragment() {

    private lateinit var noteViewModel: NewNoteDialogViewModel
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

                noteEntities = ArrayList()

                recAdapter = MyNoteRecyclerViewAdapter(noteEntities, context)
                adapter = recAdapter

                throwViewModel()
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.menu_note_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId) {
            R.id.actionAddNote -> {
                showAddNoteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun throwViewModel() {
        noteViewModel = ViewModelProviders.of(activity!!).get(NewNoteDialogViewModel::class.java)
        noteViewModel.getAllNotes().observe(activity!!, Observer {
            recAdapter.setNewNotes(it)
        })
    }

    private fun showAddNoteDialog() {
        NewNoteDialogFragment().show(activity!!.supportFragmentManager, "NewNoteDialogFramgnent")
    }
}
