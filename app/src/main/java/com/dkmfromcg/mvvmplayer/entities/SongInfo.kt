package com.dkmfromcg.mvvmplayer.entities

import android.provider.MediaStore

class SongInfo : MediaStore.Audio.AudioColumns {

    companion object{

    }

    //用于搜索、排序、分类
    var title_key: String? = null
    var artist_key: String? = null
    var album_key: String? = null

    //时间 ms
    var duration: Long = 0

    //艺术家
    var artist: String? = null

    //所属专辑
    var album: String? = null

    //专辑 ID
    var album_id: String? = null

    //专辑图片路径
    var album_path: String? = null

    //专辑录制时间
    var year: Long = 0

    //磁盘上的保存路径
    //与服务端的 path 域对应，对于同一首歌曲（文件路径相同），两者应该相同
    var data: String? = null

    //文件大小 bytes
    var size: Long = 0

    //显示的名字
    var display_name: String? = null

    //内容标题
    var title: String? = null

    //文件被加入媒体库的时间
    var date_added: Long = 0

    //文件最后修改时间
    var date_modified: Long = 0

    //MIME type
    var mime_type: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val songInfo = o as SongInfo?

        return data == songInfo!!.data

    }

    override fun hashCode(): Int {
        return data!!.hashCode()
    }

    override fun toString(): String {
        return "SongInfo{" +
                "title_key = $title_key, \n" +
                "artist_key = $artist_key, \n" +
                "album_key = $album_key, \n" +
                "duration = $duration, \n" +
                "artist = $artist, \n" +
                "album = $album, \n" +
                "album_id = $album_id, \n" +
                "album_path = $album_path, \n" +
                "year = $year, \n" +
                "data = $data, \n" +
                "size = $size, \n" +
                "display_name = $display_name, \n" +
                "title = $title, \n" +
                "date_added = $date_added, \n" +
                "date_modified = $date_modified, \n" +
                "mime_type = $mime_type \n}"

    }

}