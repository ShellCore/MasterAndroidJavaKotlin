package com.shell.android.minitwitter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.TweetsViewModel
import com.shell.android.minitwitter.extensions.showMessage
import com.shell.android.minitwitter.rest.services.tweets.GetAllTweetsCallback
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import kotlinx.android.synthetic.main.activity_dashboard.*

class TweetsFragment : Fragment(), GetAllTweetsCallback {

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }

    private val tweetsViewModel: TweetsViewModel by lazy {
        ViewModelProviders.of(activity!!)
            .get(TweetsViewModel::class.java)
    }

    // Components
    private lateinit var recTweets: RecyclerView
    private lateinit var srlTweets: SwipeRefreshLayout

    private lateinit var recAdapter: MyTweetRecyclerViewAdapter
    private var tweets: List<Tweet> = ArrayList()
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
        getComponents(view)
        setListAdapter()
        setSwipeGesture()
        return view
    }

    private fun getComponents(view: View) {
        recTweets = view.findViewById(R.id.recTweets)
        srlTweets = view.findViewById(R.id.srlTweets)
        srlTweets.setColorSchemeColors(resources.getColor(R.color.colorAccent))
    }

    override fun onGetAllTweetsError(message: String) {
        dashboardContainer.showMessage(message, Snackbar.LENGTH_LONG)
    }

    private fun setListAdapter() {

        recTweets.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(activity)
                else -> GridLayoutManager(activity, columnCount)
            }
        }
        loadTweets()
    }

    private fun setSwipeGesture() {
        srlTweets.setOnRefreshListener {
            srlTweets.isRefreshing = true
            loadNewData()
        }
    }

    private fun loadTweets() {
        getTweetsFromService()
        recAdapter = MyTweetRecyclerViewAdapter(this.context!!, tweets)
        recTweets.adapter = recAdapter
    }

    private fun getTweetsFromService() {
        tweetsViewModel.tweets.observe(activity!!, Observer { newTweets ->
            this.tweets = newTweets
            recAdapter.setNewTweets(this.tweets)
        })
    }

    private fun loadNewData() {
        tweetsViewModel.getNewTweets().observe(activity!!, object: Observer<List<Tweet>> {
            override fun onChanged(newTweets: List<Tweet>) {
                tweets = newTweets
                srlTweets.isRefreshing = false
                recAdapter.setNewTweets(tweets)
                tweetsViewModel.getNewTweets().removeObserver(this)
            }
        })
    }
}
