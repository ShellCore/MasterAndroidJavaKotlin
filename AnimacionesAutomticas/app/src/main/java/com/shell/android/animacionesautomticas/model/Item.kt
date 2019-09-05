package com.shell.android.animacionesautomticas.model

import com.shell.android.animacionesautomticas.R

data class Item(
    var id : Int,
    var name: String,
    var author: String,
    var photoUrl: Int
) {
    companion object {
        val ITEMS = arrayOf(
            Item(1, "Paris", "Cesar Morales", R.drawable.paris),
            Item(2, "Brujas", "Cesar Morales", R.drawable.bruges),
            Item(3, "Leiden", "Cesar Morales", R.drawable.leiden),
            Item(4, "Alnwick", "Cesar Morales", R.drawable.alnwick),
            Item(5, "Burano", "Cesar Morales", R.drawable.burano),
            Item(6, "Capri", "Cesar Morales", R.drawable.capri),
            Item(7, "Moulin Rouge", "Cesar Morales", R.drawable.moulin),
            Item(8, "Gaumont Opéra", "Cesar Morales", R.drawable.teathre),
            Item(9, "Venecia", "Cesar Morales", R.drawable.venice)
        )

        fun getItem(id: Int) : Item? {
            ITEMS.forEach {
                if (it.id == id) {
                    return it
                }
            }
            return null
        }
    }
}