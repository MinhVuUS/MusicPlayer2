package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.example.myapplication.adapters.SwipeSongAdapter
import com.example.myapplication.data.entities.Song
import com.example.myapplication.exoplayer.isPlaying
import com.example.myapplication.other.Status.SUCCESS
import com.example.myapplication.other.Status.ERROR
import com.example.myapplication.other.Status.LOADING
import com.example.myapplication.ui.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var glide: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//class MainActivity : AppCompatActivity() {
//
//    private val mainViewModel: MainViewModel by viewModels()
//
//    @Inject
//    lateinit var swipeSongAdapter: SwipeSongAdapter
//
//    @Inject
//    lateinit var glide: RequestManager
//
//    private var currentlyPlayingSong: Song? = null
//
//    private var playbackState: PlaybackStateCompat? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        subscribeToObservers()
//        vpSong.adapter = swipeSongAdapter
//        vpSong.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                if (playbackState?.isPlaying == true) {
//                    mainViewModel.playOrToggleSong(swipeSongAdapter.songs[position])
//                } else {
//                    currentlyPlayingSong = swipeSongAdapter.songs[position]
//                }
//            }
//        })
//        ivPlayPause.setOnClickListener {
//            currentlyPlayingSong?.let {
//                mainViewModel.playOrToggleSong(it, true)
//            }
//        }
//
//        swipeSongAdapter.setItemClickListener {
//            navHostFragment.findNavController().navigate(
//                R.id.globalActionToSongFragment
//            )
//        }
//
//        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.songFragment -> hideBottomBar()
//                R.id.homeFragment -> showBottomBar()
//                else -> showBottomBar()
//            }
//        }
//    }
//
//    private fun hideBottomBar() {
//        ivCurSongImage.isVisible = false
//        vpSong.isVisible = false
//        ivPlayPause.isVisible = false
//    }
//
//    private fun showBottomBar() {
//        ivCurSongImage.isVisible = true
//        vpSong.isVisible = true
//        ivPlayPause.isVisible = true
//    }
//
//    private fun switchViewPagerToCurrentSong(song: Song) {
//        val newItemIndex = swipeSongAdapter.songs.indexOf(song)
//        if (newItemIndex != -1) {
//            vpSong.currentItem = newItemIndex
//            currentlyPlayingSong = song
//        }
//    }
//
//    private fun subscribeToObservers() {
//        mainViewModel.mediaItems.observe(this) {
//            it?.let { result ->
//                when (result.status) {
//                    SUCCESS -> {
//                        result.data?.let { songs ->
//                            swipeSongAdapter.songs = songs
//                            if (songs.isNotEmpty()) {
//                                glide.load((currentlyPlayingSong ?: songs[0]).imageUrl)
//                                    .into(ivCurSongImage)
//                            }
//                            switchViewPagerToCurrentSong(currentlyPlayingSong ?: return@observe)
//                        }
//                    }
//                    ERROR -> Unit
//                    LOADING -> Unit
//                }
//            }
//        }
//
//        mainViewModel.currentPlayingSong.observe(this) {
//            if (it == null) return@observe
//            currentlyPlayingSong = it.toSong()
//            glide.load(currentlyPlayingSong?.imageUrl).into(ivCurSongImage)
//            switchViewPagerToCurrentSong(currentlyPlayingSong ?: return@observe)
//        }
//
//        mainViewModel.playbackState.observe(this) {
//            playbackState = it
//            ivPlayPause.setImageResource(
//                if (playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play
//            )
//        }
//
//        mainViewModel.isConnected.observe(this) {
//            it?.getContentIfNotHandled()?.let { result ->
//                when (result.status) {
//                    ERROR -> Snackbar.make(
//                        rootLayout,
//                        result.message ?: "An error occurred",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                    else -> Unit
//                }
//            }
//        }
//
//        mainViewModel.networkError.observe(this) {
//            it?.getContentIfNotHandled()?.let { result ->
//                when (result.status) {
//                    ERROR -> Snackbar.make(
//                        rootLayout,
//                        result.message ?: "An error occurred",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                    else -> Unit
//                }
//            }
//        }
//    }
//}