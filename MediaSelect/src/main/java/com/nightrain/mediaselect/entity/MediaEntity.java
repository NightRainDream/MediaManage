package com.nightrain.mediaselect.entity;

import android.net.Uri;

public class MediaEntity {
    private Uri mediaUri;
    private String mediaName;

    public MediaEntity(Uri mediaUri, String mediaName) {
        this.mediaUri = mediaUri;
        this.mediaName = mediaName;
    }

    public Uri getMediaUri() {
        return mediaUri;
    }

    public String getMediaName() {
        return mediaName;
    }
}
