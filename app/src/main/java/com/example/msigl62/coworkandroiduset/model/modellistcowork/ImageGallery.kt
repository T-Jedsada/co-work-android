package com.example.msigl62.coworkandroiduset.model.modellistcowork

import android.os.Parcel
import android.os.Parcelable


data class ImageGallery(val image_01: String? = null,
                        val image_02: String? = null,
                        val image_03: String? = null,
                        val image_04: String? = null,
                        val image_05: String? = null) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(image_01)
        writeString(image_02)
        writeString(image_03)
        writeString(image_04)
        writeString(image_05)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ImageGallery> = object : Parcelable.Creator<ImageGallery> {
            override fun createFromParcel(source: Parcel): ImageGallery = ImageGallery(source)
            override fun newArray(size: Int): Array<ImageGallery?> = arrayOfNulls(size)
        }
    }
}