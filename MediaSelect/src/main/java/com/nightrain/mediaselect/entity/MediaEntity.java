package com.nightrain.mediaselect.entity;

import android.net.Uri;

public class MediaEntity {
    private Uri mediaUri;
    private String mediaName;
    private long mediaSize;

    public MediaEntity(Uri mediaUri, String mediaName, long mediaSize) {
        this.mediaUri = mediaUri;
        this.mediaName = mediaName;
        this.mediaSize = mediaSize;
    }

    public void setMediaUri(Uri mediaUri) {
        this.mediaUri = mediaUri;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public long getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(long mediaSize) {
        this.mediaSize = mediaSize;
    }

    public Uri getMediaUri() {
        return mediaUri;
    }

    public String getMediaName() {
        return mediaName;
    }
}
