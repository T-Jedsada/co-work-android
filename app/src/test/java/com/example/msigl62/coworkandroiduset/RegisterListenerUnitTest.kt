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
    private var model = Register("", "111", "111@111.com", "111111", "111111", "",bodyPart)


    init {
        MockitoAnnotations.initMocks(this)
        registerPresenter = RegisterPresenter(view)
    }

    @Test
    fun nullOnNameValueShouldCallErrorFunction() {
        model.name = ""
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_empty_message)
    }

    @Test
    fun emailInvalidValueShouldCallErrorFunction() {
        model.email = "awefawef"
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_format_invalid)
    }

    @Test
    fun nullOnEmailValueShouldCallErrorFunction() {
        model.email = ""
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_empty_massage)
    }

    @Test
    fun nullOnPasswordValueShouldCallErrorFunction() {
        model.password = ""
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.password_empty_massage)
    }

    @Test
    fun nullOnRePasswordValueShouldCallErrorFunction() {
        model.rePassword = ""
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.re_password_empty_massage)
    }

    @Test
    fun rePasswordNotEqualPasswordShouldCallErrorFunction() {
        model.rePassword = "popopopoppo"
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.invalid_re_password)
    }

    @Test
    fun nameLongerThanConfigDefaultShouldCallErrorFunction() {
        model.name = "aiwjfwoeiaweoijfaowiejfowifjoawjefoawijefoiajwefojawoefijaweofjawoefjaowejfaowejfoaweifjoawijefoaiwjeofijaw"
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_longer_that_default)
    }

}