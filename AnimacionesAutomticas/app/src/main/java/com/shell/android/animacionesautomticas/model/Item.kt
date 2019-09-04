package com.shell.android.animacionesautomticas.model

data class Item(
    var id: Int,
    var name: String,
    var author: String,
    var photoUtl: String,
    var ThumbnailUrl: String
) {
    companion object {
        val ITEMS = arrayOf(
            Item(1, "Título 1", "Autor 1", "", "")
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