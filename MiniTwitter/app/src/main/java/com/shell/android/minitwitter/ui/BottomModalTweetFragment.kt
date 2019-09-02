package com.shell.android.minitwitter.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.shell.android.minitwitter.R
import com.shell.android.minitwitter.data.TweetsViewModel
import kotlinx.android.synthetic.main.bottom_modal_tweet_fragment.*

class BottomModalTweetFragment : BottomSheetDialogFragment() {

    companion object {
        const val TWEET_ID = "tweet_id"

        fun newInstance(idTweet: Int): BottomModalTweetFragment {
            val dialog = BottomModalTweetFragment()
            val args = Bundle()
            args.putInt(TWEET_ID, idTweet)
            dialog.arguments = args
            return dialog
        }
    }

    private lateinit var viewModel: TweetsViewModel
    private var idTweet: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            idTweet = getInt(TWEET_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_modal_tweet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(TweetsViewModel::class.java)
        setNavBottomListener()
    }

    private fun setNavBottomListener() {
        navBottomTweet.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_delete_tweet -> {
                    deleteTweet()
                    true
                }
                else -> false
            }
        }
    }

    private fun deleteTweet() {
        viewModel.deleteTweet(idTweet)
        dialog.dismiss()
    }

}
