package com.shell.android.minitwitter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.services.tweet.response.Tweet

class TweetsFragment : Fragment() {

    private lateinit var recTweets : RecyclerView
    private lateinit var recAdapter: MyTweetRecyclerViewAdapter
    private lateinit var tweets : List<Tweet>

    private var columnCount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                recTweets = view
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                loadTweets()
            }
        }
        return view
    }

    private fun loadTweets() {
        recAdapter = MyTweetRecyclerViewAdapter(this.context!!, tweets)
        recTweets.adapter = recAdapter
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
