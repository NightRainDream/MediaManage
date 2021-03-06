package com.nightrain.mediaselect.util

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.listener.LoadMediaCallback
import java.io.File

private const val TAG = "MediaDataTool"

object MediaDataTool {

    fun loadGIFResources(context: Context, listener: LoadMediaCallback) {
        if (checkPermission(context)) {
            Thread {
                val gifs = mutableListOf<MediaEntity>()
                val projection = arrayOf(
                    MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DISPLAY_NAME,
                    MediaStore.Files.FileColumns.SIZE
                )
                val select = "(_data LIKE '%.gif')"
                val cursor = context.contentResolver.query(
                    MediaStore.Files.getContentUri("external"),
                    projection,
                    select,
                    null,
                    MediaStore.Files.FileColumns.DATE_MODIFIED + "  desc"
                )
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val id =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Files.getContentUri("external"), id
                        )
                        val fileName =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME))
                        val fileSize =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE))
                        gifs.add(
                            MediaEntity(
                                uri,
                                fileName,
                                fileSize
                            )
                        )
                    }
                    cursor.close()
                    listener.loadSuccess(gifs)
                }
            }.start()
        } else {
            Log.w(TAG, "loadVideoResources: 请检查读写权限是否打开")
        }
    }


    fun loadImageResources(
        context: Context,
        isDisplayGIF: Boolean = false,
        listener: LoadMediaCallback
    ) {
        if (checkPermission(context)) {
            Thread {
                val images = mutableListOf<MediaEntity>()
                val projection = arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.SIZE
                )
                val cursor = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    MediaStore.Images.ImageColumns.DATE_MODIFIED + "  desc"
                )
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val id =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                        //Uri
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                        )
                        //文件名称
                        val fileName =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))

                        val fileSize =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.SIZE))
                        if (isDisplayGIF) {
                            images.add(
                                MediaEntity(
                                    uri,
                                    fileName,
                                    fileSize
                                )
                            )
                        } else {
                            if (!fileName.contains(".gif")) {
                                images.add(
                                    MediaEntity(
                                        uri,
                                        fileName,
                                        fileSize
                                    )
                                )
                            }
                        }
                    }
                    cursor.close()
                    listener.loadSuccess(images)
                }
            }.start()
        } else {
            Log.w(TAG, "loadVideoResources: 请检查读写权限是否打开")
        }
    }

    fun loadVideoResources(context: Context, listener: LoadMediaCallback) {
        if (checkPermission(context)) {
            Thread {
                val videos = mutableListOf<MediaEntity>()
                val projection = arrayOf(
                    MediaStore.Video.VideoColumns._ID,
                    MediaStore.Video.VideoColumns.DISPLAY_NAME,
                    MediaStore.Video.VideoColumns.SIZE
                )
                val cursor = context.contentResolver.query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    MediaStore.Video.VideoColumns.DATE_MODIFIED + "  desc"
                )
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val id =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id
                        )
                        val fileName =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME))
                        val fileSize =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE))
                        videos.add(MediaEntity(uri, fileName,fileSize))
                    }
                    cursor.close()
                    listener.loadSuccess(videos)
                }
            }.start()
        } else {
            Log.w(TAG, "loadVideoResources: 请检查读写权限是否打开")
        }
    }

    fun loadAudioResources(context: Context, listener: LoadMediaCallback) {
        if (checkPermission(context)) {
            Thread {
                val audios = mutableListOf<MediaEntity>()
                val projection = arrayOf(
                    MediaStore.Audio.AudioColumns._ID,
                    MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                    MediaStore.Audio.AudioColumns.SIZE
                )
                val cursor = context.contentResolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    MediaStore.Audio.AudioColumns.DATE_MODIFIED + "  desc"
                )
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        val id =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
                        )
                        val fileName =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME))
                        val fileSize =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE))
                        audios.add(MediaEntity(uri, fileName,fileSize))
                    }
                    cursor.close()
                    listener.loadSuccess(audios)
                }
            }.start()
        } else {
            Log.w(TAG, "loadVideoResources: 请检查读写权限是否打开")
        }
    }


    private fun checkPermission(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }
}