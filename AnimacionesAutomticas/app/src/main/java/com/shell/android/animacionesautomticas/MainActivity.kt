package com.shell.android.animacionesautomticas

import android.content.Intent
import android.os.Bundle
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
import com.shell.android.animacionesautomticas.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private val adapter: GridAdapter by lazy {
        GridAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridItems.onItemClickListener = this
        gridItems.adapter = adapter
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View, position: Int, id: Long) {
        val item = adapterView!!.getItemAtPosition(position) as Item
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID_KEY, item.id)

        val imgItem: ImageView = view.findViewById(R.id.imgItem)
        val txtItem: TextView = view.findViewById(R.id.imgItem)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            Pair<View, String>(imgItem, DetailActivity.SHARED_VIEW_PHOTO),
            Pair<View, String>(txtItem, DetailActivity.SHARED_VIEW_TITLE)
        )
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    private class GridAdapter : BaseAdapter() {

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItem(p0: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(p0: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
