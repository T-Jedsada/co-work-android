package com.example.msigl62.coworkandroiduset.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseSuggestion(@SerializedName("success") val noticeMessage: String?
                              , @SerializedName("data") val data: List<DataCoWorkNearby>?)

data class DataCoWorkNearby(@SerializedName("_id") val id: String,
                            @SerializedName("name") val name: String,
                            @SerializedName("address") val address: String,
                            @SerializedName("latitude") val latitude: Double,
                            @SerializedName("longitude") val longitude: Double,
                            @SerializedName("provider_id") val providerId: String,
                            @SerializedName("gellery") val gallery: Gallery,
                            @SerializedName("status") val status: String,
                            @SerializedName("poster") val poster: String,
                            @SerializedName("average_rating") val averageRating: String)

data class Gallery(@SerializedName("image_01") val img1: String,
                   @SerializedName("image_02") val img2: String,
                   @SerializedName("image_03") val img3: String,
                   @SerializedName("image_04") val img4: String,
                   @SerializedName("image_05") val img5: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(img1)
        writeString(img2)
        writeString(img3)
        writeString(img4)
        writeString(img5)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Gallery> = object : Parcelable.Creator<Gallery> {
            override fun createFromParcel(source: Parcel): Gallery = Gallery(source)
            override fun newArray(size: Int): Array<Gallery?> = arrayOfNulls(size)
        }
    }
}