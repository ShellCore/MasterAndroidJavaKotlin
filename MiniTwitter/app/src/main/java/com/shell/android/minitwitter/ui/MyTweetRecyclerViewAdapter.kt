package com.shell.android.minitwitter.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.TweetsViewModel
import com.shell.android.minitwitter.extensions.getCredentialFromSharedPreferences
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import kotlinx.android.synthetic.main.fragment_tweets.view.*


class MyTweetRecyclerViewAdapter(
    val context: Context,
    var tweets: List<Tweet>
) : RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder>() {

    private val viewModel: TweetsViewModel = ViewModelProviders.of(context as FragmentActivity)
        .get(TweetsViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tweets, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.bind(tweet)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = tweets.size

    fun setNewTweets(tweets: List<Tweet>) {
        this.tweets = tweets
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var tweet: Tweet

        fun bind(tweet: Tweet) {
            this.tweet = tweet
            with(this.tweet) {
                setUserData(this)
                setUserPhoto(this)
                validateIfLikedByActualUser(this)
                setOnClickListeners(this)
            }
        }

        private fun setOnClickListeners(tweet: Tweet) {
            view.btnLike.setOnClickListener {
                viewModel.likeTweet(tweet.id)
            }
        }

        private fun validateIfLikedByActualUser(tweet: Tweet) {
            val actualUser = getCredentialFromSharedPreferences()
            if (tweet.likes.isNotEmpty()) {
                for (user in tweet.likes) {
                    if (actualUser!!.userName == user.username) {
                        setLikeByMyself()
                        break
                    } else {
                        setNotLikedByMe()
                    }
                }
            }
        }

        private fun setUserData(tweet: Tweet) {
            with(tweet) {
                view.txtUsername.text = user.username
                view.txtMessage.text = mensaje
                view.txtLikeCount.text = "${likes.size}"
            }
        }

        private fun setUserPhoto(tweet: Tweet) {
            if (tweet.user.photoUrl.isNotEmpty()) {
                Glide.with(context)
                    .load("https://www.minitwitter.com/apiv1/uploads/photos/${tweet.user.photoUrl}")
                    .into(view.imgPhoto)
            }
        }

        private fun setLikeByMyself() {
            view.btnLike.setImageResource(R.drawable.ic_heart_me)
        }

        private fun setNotLikedByMe() {
            view.btnLike.setImageResource(R.drawable.ic_heart_empty)
        }
    }
}
