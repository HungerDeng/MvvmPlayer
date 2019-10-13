package com.dkmfromcg.mvvmplayer.model

import android.support.v4.media.MediaMetadataCompat


interface IMediaDatasProvider {

    companion object{
        @JvmField
        val CUSTOM_METADATA_TRACK_SOURCE = "__SOURCE__"
    }

    fun iterator(): Iterator<MediaMetadataCompat>
}