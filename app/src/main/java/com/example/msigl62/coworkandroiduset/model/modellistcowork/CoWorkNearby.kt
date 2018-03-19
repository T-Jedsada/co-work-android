package com.example.msigl62.coworkandroiduset.model.modellistcowork

import com.google.gson.annotations.SerializedName

//TODO model  Wait API CoWorkNearby
data class CoWorkNearby (@SerializedName("coworking_id") val id :String ,@SerializedName("name_co-working") val name :String
                         ,@SerializedName("provider_id") val providerId :String ,@SerializedName("status") val status :String
                         ,@SerializedName("average_rating") val averageRating :String )