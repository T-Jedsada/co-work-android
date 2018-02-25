package com.example.msigl62.coworkandroiduset.ui.register

interface RegisterContact {

    interface Presenter {
        fun checkEdiText(model: RegisterContactModel, view : View)
    }
    interface View {
        fun contactPresenter(Status:String,id_facebook:String?=null,name:String?=null,email:String?=null,password:String?=null,path_image:String?=null)
    }

}