package com.example.msigl62.coworkandroiduset.model.modellistcowork

data class CoWorkPopular (val coworking_id:String?=null,
                          val details:String?=null,
                          val latitude:Double?=null,
                          val longitude:Double?=null,
                          val name_coworking:String?=null,
                          val price_per_hour:String?=null,
                          val provider_id:String?=null,
                          val status:Boolean?=null,
                          val cover_image:ImageGallery?=null)

//TODO model  Wait API CoWorkPopular