package com.example.jetpackconstraintlayout.ui

import android.app.AlertDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import androidx.fragment.app.DialogFragment

import com.example.jetpackconstraintlayout.R
import kotlinx.android.synthetic.main.new_note_dialog_fragment.*

class NewNoteDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = NewNoteDialogFragment()
    }

    private lateinit var dialogView: View
    private lateinit var viewModel: NewNoteDialogViewModel

    private lateinit var edtTitle: EditText
    private lateinit var edtContent: EditText
    private lateinit var rgColors: RadioGroup
    private lateinit var swFavorite: Switch

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewNoteDialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = layoutInflater.inflate(R.layout.new_note_dialog_fragment, null)
        getComponents(dialogView)

        var builder = AlertDialog.Builder(activity)
        builder.apply {
            setView(dialogView)
            setMessage("Introduzca los datos de la nueva nota")
            setTitle("Nueva nota")
            setPositiveButton("Guardar") { _, _ ->
                saveNote()
            }
            setNegativeButton("Cancelar") { _, _ ->
                // TODO
            }
        }

        return builder.create()
    }

    private fun saveNote() {
        val title = edtTitle.text
        val content = edtContent.text
        val color = when(rgColors.checkedRadioButtonId) {
            R.id.rbRed -> "rojo"
            R.id.rbGreen -> "verde"
            else -> "azul"
        }
        val fav = swFavorite.isChecked
    }

    private fun getComponents(view: View) {
        edtTitle = view.findViewById(R.id.edtTitle)
        edtContent = view.findViewById(R.id.edtContent)
        rgColors = view.findViewById(R.id.rgColors)
        swFavorite = view.findViewById(R.id.swFavorite)
    }
}
