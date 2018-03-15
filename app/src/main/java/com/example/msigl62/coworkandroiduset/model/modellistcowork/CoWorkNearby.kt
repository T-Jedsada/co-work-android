package com.example.msigl62.coworkandroiduset.model.modellistcowork

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

//TODO model  Wait API CoWorkNearby
data class CoWorkNearby (val id: Int? = null,
                        val title: String? = null,
                        val is_schedule: String? = null,
                        val header_blog_image: String? = null,
                        val blogger_id: String? = null,
                        val status: String? = null) : Parcelable {
    constructor(source: Parcel) : this(
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString())
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(title)
        writeString(is_schedule)
        writeString(header_blog_image)
        writeString(blogger_id)
        writeString(status) }
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CoWorkNearby> = object : Parcelable.Creator<CoWorkNearby> {
            override fun createFromParcel(source: Parcel): CoWorkNearby = CoWorkNearby(source)
            override fun newArray(size: Int): Array<CoWorkNearby?> = arrayOfNulls(size) } } }

data class ListCoWorkNearby(@SerializedName("data") var results: List<CoWorkNearby>? = null)