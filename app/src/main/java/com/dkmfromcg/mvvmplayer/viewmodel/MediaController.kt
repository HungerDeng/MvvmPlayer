package com.dkmfromcg.mvvmplayer.viewmodel

import com.dkmfromcg.mvvmplayer.model.MusicProvider

class MediaController {

    private val mMusicProvider = MusicProvider

    fun setPlayMode(playMode: MusicProvider.PlayMode){
        mMusicProvider.setPlayMode(playMode)

    }
}