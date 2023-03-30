package com.example.myapplication.exoplayer.callback

import android.app.Service
import android.widget.Toast
import com.example.myapplication.exoplayer.MusicService
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player

//class MusicPlayerEventListener(
//    private val musicService: MusicService
//
//
//    ) : Player.Listener{
//
//
//    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//        super.onPlayWhenReadyChanged(playWhenReady, playbackState)
//        if(playbackState == Player.STATE_READY && !playWhenReady) {
//            musicService.stopForeground(false)
//        }
//    }
//
//    override fun onPlayerError(error: PlaybackException) {
//        super.onPlayerError(error)
//        Toast.makeText(musicService, "An unknow error occured", Toast.LENGTH_LONG).show()
//    }
//}
class MusicPlayerEventListener(
    private val musicService: MusicService
) : Player.Listener {

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, playbackState)
        if(playbackState == Player.STATE_READY && !playWhenReady) {
            musicService.stopForeground(Service.STOP_FOREGROUND_DETACH)
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService, "An unknown error occured", Toast.LENGTH_LONG).show()
    }

}