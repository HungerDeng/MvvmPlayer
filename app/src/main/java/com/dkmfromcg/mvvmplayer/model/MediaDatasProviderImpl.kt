package com.dkmfromcg.mvvmplayer.model

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import android.support.v4.media.MediaMetadataCompat
import com.dkmfromcg.mvvmplayer.entities.SongInfo
import com.dkmfromcg.mvvmplayer.util.StringUtils

class MediaDatasProviderImpl(val context: Context) : IMediaDatasProvider {


    override fun iterator(): Iterator<MediaMetadataCompat> {
        val mediaMetadatas = mutableListOf<MediaMetadataCompat>()
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor ?: return mediaMetadatas.iterator()
        var albumTitle: String? = null
        var albumArtist: String? = null
        var albumIconUri: String? = null

        var title: String? = null
        var artist: String? = null
        var data: String? = null


        while (cursor.moveToNext()) {
            val song = SongInfo()

            song.title = (cursor.getString(cursor.getColumnIndex(SongInfo.TITLE)))
            song.artist = (cursor.getString(cursor.getColumnIndex(SongInfo.ARTIST)))


            song.data = (cursor.getString(cursor.getColumnIndex(SongInfo.DATA)))
            song.display_name = (cursor.getString(cursor.getColumnIndex(SongInfo.DISPLAY_NAME)))
            song.mime_type = (cursor.getString(cursor.getColumnIndex(SongInfo.MIME_TYPE)))
            song.year = (cursor.getLong(cursor.getColumnIndex(SongInfo.YEAR)))
            song.duration = (cursor.getLong(cursor.getColumnIndex(SongInfo.DURATION)))
            song.size = (cursor.getLong(cursor.getColumnIndex(SongInfo.SIZE)))

            song.date_added = (cursor.getLong(cursor.getColumnIndex(SongInfo.DATE_ADDED)))
            song.date_modified = (cursor.getLong(cursor.getColumnIndex(SongInfo.DATE_MODIFIED)))

            albumTitle = cursor.getString(cursor.getColumnIndex(AudioColumns.ALBUM))
            albumArtist = cursor.getString(cursor.getColumnIndex(AudioColumns.ALBUM_ARTIST))
            albumIconUri = getAlbumArtPicPath(
                context,
                cursor.getString(cursor.getColumnIndex(AudioColumns.ALBUM_ID))
            )
            title = cursor.getString(cursor.getColumnIndex(AudioColumns.TITLE))
            artist = cursor.getString(cursor.getColumnIndex(AudioColumns.TITLE))

            val item = MediaMetadataCompat.Builder()
                /**
                 * The album title for the media.
                 */
                .putString(
                    MediaMetadataCompat.METADATA_KEY_ALBUM,
                    cursor.getString(SongInfo.ALBUM)
                )
                /**
                 * The artist for the album of the media's original source.
                 */
                .putString(
                    MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST,
                    cursor.getString(SongInfo.ALBUM_ARTIST)
                )
                /**
                 * The artwork for the album of the media's original source as a Uri style
                 * String.
                 */
                .putString(
                    MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
                    getAlbumArtPicPath(
                        context,
                        cursor.getString(cursor.getColumnIndex(SongInfo.Companion.ALBUM_ID))
                    )
                )


                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)

                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, id)
                .putString(
                    IMediaDatasProvider.CUSTOM_METADATA_TRACK_SOURCE,
                    cursor.getString(cursor.getColumnIndex(SongInfo.DATA))
                )

                /**
                 * The genre of the media.
                 * 类型
                 */
                .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                /**
                 * The track number for the media.
                 */
                .putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, TRACK)

                .build()
            mediaMetadatas.add(item)
        }
        cursor.close()
        return mediaMetadatas.iterator()
    }

    //根据专辑 id 获得专辑图片保存路径
    @Synchronized
    private fun getAlbumArtPicPath(context: Context, albumId: String): String? {

        // 小米应用商店检测crash ，错误信息：[31188,0,com.duan.musicoco,13155908,java.lang.IllegalStateException,Unknown URL: content://media/external/audio/albums/null,Parcel.java,1548]
        if (!StringUtils.isReal(albumId)) {
            return null
        }

        val projection = arrayOf(MediaStore.Audio.Albums.ALBUM_ART)
        var imagePath: String? = null
        val uri =
            Uri.parse("content://media" + MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI.path + "/" + albumId)
        val contentResolver = context.contentResolver
        val cur = contentResolver.query(uri, projection, null, null, null) ?: return null
        if (cur.count > 0 && cur.columnCount > 0) {
            cur.moveToNext()
            imagePath = cur.getString(0)
        }
        cur.close()


        return imagePath
    }
}