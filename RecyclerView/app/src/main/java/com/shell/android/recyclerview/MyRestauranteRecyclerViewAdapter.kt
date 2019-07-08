package com.shell.android.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shell.android.recyclerview.RestauranteFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_restaurante.view.*

class MyRestauranteRecyclerViewAdapter(
    private val mValues: List<Restaurante>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyRestauranteRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Restaurante
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_restaurante, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Rescatamos los datos del elemento que ocupa la posición "position"
        holder.bind(mValues[position])
        val item = mValues[position]

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        lateinit var mItem : Restaurante

        override fun toString(): String {
            return super.toString() + " '" + mView.txtRestauranteName.text + "'"
        }

        fun bind(restaurante: Restaurante) {
            mItem = restaurante
            mView.apply {
                txtRestauranteName.text = mItem.nombre
                txtRestauranteAddress.text = mItem.direccion
                rtnRestauranteVal.rating = mItem.valoracion
                Picasso.get()
                    .load(mItem.urlPhoto)
                    .resize(400, 150)
                    .centerCrop()
                    .into(imgRestaurantePhoto)
            }
        }
    }
}
