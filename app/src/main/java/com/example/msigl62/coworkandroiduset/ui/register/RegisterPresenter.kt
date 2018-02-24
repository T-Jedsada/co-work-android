package com.example.msigl62.coworkandroiduset.ui.register
import java.util.regex.Pattern

class RegisterPresenter : RegisterContact.Presenter {
    override fun checkEdiText(model: RegisterContactModel, view: RegisterContact.View) {
        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.edit_email)
        when (matcher.matches()) {
            matcher.matches().equals("") -> view.contactPresenter("emailFail")
            else -> {
                when (model.edit_name) {
                    ""->view.contactPresenter("nameFail")
                    else -> {
                        val x:Int? =model.edit_name?.length
                        if(x!! >30){
                            view.contactPresenter("name>30")
                        }else{
                            when (model.edit_password) {
                                "" ->view.contactPresenter("PassFail")
                                else -> {
                                    if(model.edit_password.equals(model.edit_re_password)){
                                        val i:Int? =model.edit_password?.length
                                        if(i==6){
                                            view.contactPresenter("PassTure")
                                        }else{
                                            view.contactPresenter("PassFail>6or<6")
                                        }
                                    }else{
                                        view.contactPresenter("PassFail")
                                    }
                                } } } } } }
    }}}


