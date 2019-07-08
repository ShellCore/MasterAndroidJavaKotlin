package com.shell.android.recyclerview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RestauranteFragment.OnListFragmentInteractionListener] interface.
 */
class RestauranteFragment : Fragment() {

    private lateinit var recView: RecyclerView
    private lateinit var recAdapter: MyRestauranteRecyclerViewAdapter

    private lateinit var restaurantes : ArrayList<Restaurante>

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurante_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            recView = view as RecyclerView;
            with(recView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                // Lista de elementos (Restaurantes)
                restaurantes = ArrayList()
                restaurantes.addAll(listOf(
                    Restaurante("Pizerria Carlos", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80", 4.0f, "Madrid, España"),
                    Restaurante("Hamburquesería rápida", "https://i.ytimg.com/vi/BfCwN4iy6T8/maxresdefault.jpg", 5.0f, "Distrito Federal, México")
                ))

                // Asociamos el adaptador al RecyclerView
                recAdapter = MyRestauranteRecyclerViewAdapter(restaurantes, listener)
                adapter = recAdapter
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Restaurante?)
    }

}
