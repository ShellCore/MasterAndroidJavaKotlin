package com.shell.android.animacionesautomticas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shell.android.animacionesautomticas.model.Item
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var item: Item? = null

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
        item = Item.getItem(id)

        // Conexión de los elementos compartidos
        ViewCompat.setTransitionName(imgPhotoDetail, SHARED_VIEW_PHOTO)
        ViewCompat.setTransitionName(txtTitleDetail, SHARED_VIEW_TITLE)

        if (item != null) {
            loadItem()
        }
    }

    private fun loadItem() {
        Glide.with(applicationContext)
            .load(item!!.photoUrl)
            .centerCrop()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imgPhotoDetail)

        txtTitleDetail.text = item!!.name
    }
}
