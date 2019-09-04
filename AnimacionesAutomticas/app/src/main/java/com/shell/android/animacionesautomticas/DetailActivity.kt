package com.shell.android.animacionesautomticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shell.android.animacionesautomticas.model.Item

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID_KEY = "id"
        const val SHARED_VIEW_PHOTO = "shared_photo"
        const val SHARED_VIEW_TITLE = "shared_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        val id = bundle!!.getInt(ID_KEY)
        val item = Item.getItem(id)

        loadItem(item!!)
    }

    private fun loadItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
