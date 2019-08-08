package com.shell.android.minitwitter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.extensions.showMessage
import com.shell.android.minitwitter.rest.services.tweets.GetAllTweetsCallback
import com.shell.android.minitwitter.rest.services.tweets.TweetsRepository
import com.shell.android.minitwitter.rest.services.tweets.TweetsRepositoryImpl
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import kotlinx.android.synthetic.main.activity_dashboard.*

class TweetsFragment : Fragment(), GetAllTweetsCallback {

    private lateinit var repository : TweetsRepository

    private lateinit var recTweets : RecyclerView
    private lateinit var recAdapter: MyTweetRecyclerViewAdapter
    private var tweets : List<Tweet> = ArrayList()

    private var columnCount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRepository()

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

    override fun onGetAllTweetsSuccess(tweets: List<Tweet>) {
        this.tweets = tweets
        recAdapter.tweets = this.tweets
        recAdapter.notifyDataSetChanged()
    }

    override fun onGetAllTweetsError(message: String) {
        dashboardContainer.showMessage(message, Snackbar.LENGTH_LONG)
    }

    private fun initRepository() {
        repository = TweetsRepositoryImpl(activity!!, this)
    }

    private fun loadTweets() {
        getTweetsFromService()
        recAdapter = MyTweetRecyclerViewAdapter(this.context!!, tweets)
        recTweets.adapter = recAdapter
    }

    private fun getTweetsFromService() {
        repository.getAllTweets()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
