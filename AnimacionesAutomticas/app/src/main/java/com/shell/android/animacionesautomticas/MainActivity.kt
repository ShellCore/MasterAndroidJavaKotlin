package com.shell.android.animacionesautomticas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shell.android.animacionesautomticas.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var adapter: GridAdapter
    private val items = Item.ITEMS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GridAdapter(applicationContext, items)
        gridList.adapter = adapter
        gridList.setOnItemClickListener(this)
    }

    override fun onItemClick(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = items[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_ID, item.id)

        val imgItem : ImageView = view!!.findViewById(R.id.imgPhoto)
        val txtTitle : TextView = view!!.findViewById(R.id.txtTitle)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair<View, String>(imgItem, DetailActivity.SHARED_VIEW_PHOTO),
            Pair<View, String>(txtTitle, DetailActivity.SHARED_VIEW_TITLE)
        )
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    private class GridAdapter(val context: Context, val items: Array<Item>) : BaseAdapter() {

        private val inflater by lazy {
            LayoutInflater.from(context)
        }

        override fun getView(position: Int, viewConverter: View?, parent: ViewGroup?): View {
            val v = inflater.inflate(R.layout.item, null)

            val item = items[position]

            val imgPhoto: ImageView = v.findViewById(R.id.imgPhoto)
            val txtTitle: TextView = v.findViewById(R.id.txtTitle)

            Glide.with(context)
                .load(item.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .into(imgPhoto)

            txtTitle.text = item.name
            return v
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return items.size
        }

    }
}
