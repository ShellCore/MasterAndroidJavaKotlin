package com.shell.android.minitwitter.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import kotlinx.android.synthetic.main.fragment_tweets.view.*


class MyTweetRecyclerViewAdapter(
    val context: Context,
    var tweets: List<Tweet>
) : RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tweets, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.bind(tweet)
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
            this.tweet.apply {
                view.txtUsername.text = user.username
                view.txtMessage.text = mensaje
                view.txtLikeCount.text = "${likes.size}"
                setUserPhoto()

                if (likes.isNotEmpty() && likes.contains(user)) {
                    setLikeByMyself()
                } else {
                    setNotLikedByMe()
                }
            }
        }

        private fun Tweet.setUserPhoto() {
            if (user.photoUrl.isNotEmpty()) {
                Glide.with(context)
                    .load("https://www.minitwitter.com/apiv1/uploads/photos/${user.photoUrl}")
                    .into(view.imgPhoto)
            }
        }

        private fun setLikeByMyself() {
            Glide.with(context)
                .load(R.drawable.ic_heart_me)
                .into(view.btnLike)
        }

        private fun setNotLikedByMe() {
            Glide.with(context)
                .load(R.drawable.ic_heart_empty)
                .into(view.btnLike)
        }
    }
}
