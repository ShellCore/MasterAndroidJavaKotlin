package com.shell.android.animacionesautomticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shell.android.animacionesautomticas.model.Item

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_ID = "id_key"
        const val SHARED_VIEW_PHOTO = "shared_view_photo"
        const val SHARED_VIEW_TITLE = "shared_view_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        val id = bundle!!.getInt(KEY_ID)
        val item = Item.getItem(id)

        if (item != null) {
            loadItem(item)
        }
    }

    private fun loadItem(item: Item) {
        // TODO
    }
}
