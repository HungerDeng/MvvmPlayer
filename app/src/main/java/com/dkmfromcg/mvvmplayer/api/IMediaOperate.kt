package com.dkmfromcg.mvvmplayer.api

interface IMediaOperate {

    fun reset()

    fun prepare()

    fun play()

    fun seekTo(time: Long)

    fun pause()

    fun stop()

    /**
     * 上一首
     */
    fun previous()

    /**
     * 下一首
     */
    fun next()

    fun release()
}