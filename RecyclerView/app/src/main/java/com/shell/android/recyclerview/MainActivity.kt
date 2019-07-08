package com.shell.android.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shell.android.recyclerview.dummy.DummyContent

class MainActivity : AppCompatActivity(), RestauranteFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
