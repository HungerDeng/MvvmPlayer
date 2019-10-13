package com.dkmfromcg.mvvmplayer.model

import com.dkmfromcg.mvvmplayer.entities.Song
import com.dkmfromcg.mvvmplayer.util.Logger

object MusicProvider {

    const val TAG = "MusicProvider"

    var mPlayMode: PlayMode = getPlayModeFormSp()
    var mMusicList: List<Song> = mutableListOf()

    enum class PlayMode {
        /**
         * 拼音顺序播放
         */
        ORDER,
        /**
         * 随机播放
         */
        RANDOM,
        /**
         * 循环播放
         */
        LOOP
    }

    @Volatile
    private var initialized = false

    fun prepareMediaSource() {
        if (initialized == true){
            Logger.d(TAG, "MediaSource has been initialized")
            return
        }
        //TODO 加载数据


    }

    private fun getPlayModeFormSp(): PlayMode {
        return PlayMode.ORDER
    }

    @Synchronized
    fun setPlayMode(playMode: PlayMode) {
        if (playMode == mPlayMode) {
            return
        }
        mPlayMode = playMode
        when (playMode) {
            PlayMode.ORDER -> {
                mMusicList = getOrderList()
            }
            PlayMode.RANDOM -> {
                mMusicList = getRandomList()
            }
            PlayMode.LOOP -> {
                mMusicList = getLoopList()
            }
        }
    }


    private fun getOrderList(): List<Song> {
        return mutableListOf()
    }

    private fun getRandomList(): List<Song> {
        return mutableListOf()
    }

    private fun getLoopList(): List<Song> {
        return mutableListOf()
    }
}