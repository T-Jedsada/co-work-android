package com.example.msigl62.coworkandroiduset

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.ui.register.RegisterContact
import com.example.msigl62.coworkandroiduset.ui.register.RegisterPresenter
import okhttp3.MultipartBody
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory.times

class RegisterUnitTest {

    private val registerPresenter: RegisterPresenter
    @Mock
    private val view = mock(RegisterContact.View::class.java)
    @Mock
    private val bodyPart = mock(MultipartBody.Part::class.java)



    init {
        MockitoAnnotations.initMocks(this)
        registerPresenter = RegisterPresenter(view)
    }

    @Test
    fun nullOnNameValueShouldCallErrorFunction() {
        val model = Register("", "", "erijg@sergsergserg"
                , "aweeej", "aweeej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_empty_message)
    }

    @Test
    fun emailInvalidValueShouldCallErrorFunction() {
        val model = Register("", "awekwpefaowkfe", "erijg"
                , "aeewej", "aweeej", bodyPart)

        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_format_invalid)
    }

    @Test
    fun nullOnEmailValueShouldCallErrorFunction() {
        val model = Register("", "porkgeprok", ""
                , "aweeej", "aweeej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_empty_massage)
    }

    @Test
    fun nullOnPasswordValueShouldCallErrorFunction() {
        val model = Register("", "porkgeprok", "eeeeee@gmailc.com"
                , "", "aweeej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.password_empty_massage)
    }

    @Test
    fun nullOnRePasswordValueShouldCallErrorFunction() {
        val model = Register("", "porkgeprok", "pokpeokgeprogk@pokew.com"
                , "aweeej", "", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.re_password_empty_massage)
    }

    @Test
    fun rePasswordNotEqualPasswordShouldCallErrorFunction() {
        val model = Register("", "porkgeprok", "wefwefp@woekf.com"
                , "aweeej", "ewwegweg", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.invalid_re_password)
    }

    @Test
    fun nameLongerThanConfigDefaultShouldCallErrorFunction() {
        val model = Register("", "porkgeeprogpwrjgo;iwaejgo;awijrg;owijriiiiiiiiiiiiiiiiiiiiiiiiiiiwrgojwjig;poiejgwpijeg;owiejgprok"
                , "wefwefwef@weok.com"
                , "aweeej", "aweeej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_longer_that_default)
    }

    @Test
    fun passWordThanConfigDefaultShouldCallErrorFunction() {
        val model = Register("", "awrgegrg"
                , "sssssssssssssssssssssssssss@ewfw.com"
                , "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                , "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.password_longer_that_defaul)
    }
}