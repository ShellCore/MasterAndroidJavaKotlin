package com.shell.android.minitwitter.ui.tweets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        const val TWEET_LIST_TYPE = "tweet_list_type"
        const val TWEET_LIST_ALL = 1
        const val TWEET_LIST_FAVS = 2

        fun newInstance(tweetListType: Int): TweetsFragment {
            val args = Bundle()
            args.putInt(TWEET_LIST_TYPE, tweetListType)

            val fragment = TweetsFragment()
            fragment.arguments = args
            return fragment
        }
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
    private var tweetListType =
        TWEET_LIST_ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            tweetListType = it.getInt(TWEET_LIST_TYPE)
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
        recTweets.layoutManager = LinearLayoutManager(activity)
        loadTweets()
    }

    private fun setSwipeGesture() {
        srlTweets.setOnRefreshListener {
            srlTweets.isRefreshing = true
            when(tweetListType) {
                TWEET_LIST_ALL -> loadNewData()
                TWEET_LIST_FAVS -> loadNewFavData()
            }
        }
    }

    private fun loadTweets() {
        recAdapter = MyTweetRecyclerViewAdapter(
            this.context!!,
            tweets
        )
        recTweets.adapter = recAdapter

        when(tweetListType) {
            TWEET_LIST_ALL -> getTweetsFromViewModel()
            TWEET_LIST_FAVS -> getFavTweetsFromViewModel()
        }
    }

    private fun getTweetsFromViewModel() {
        tweetsViewModel.tweets.observe(activity!!, Observer { newTweets ->
            this.tweets = newTweets
            recAdapter.setNewTweets(this.tweets)
        })
    }

    private fun getFavTweetsFromViewModel() {
        tweetsViewModel.favTweets.observe(activity!!, Observer { newTweets ->
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

    private fun loadNewFavData() {
        tweetsViewModel.getNewFavTweets().observe(activity!!, object: Observer<List<Tweet>> {
            override fun onChanged(newTweets: List<Tweet>) {
                tweets = newTweets
                srlTweets.isRefreshing = false
                recAdapter.setNewTweets(tweets)
                tweetsViewModel.getNewFavTweets().removeObserver(this)
            }
        })
    }
}
