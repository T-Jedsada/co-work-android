package com.example.msigl62.coworkandroiduset

import android.content.Context
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
    @Mock
    private val context = mock(Context::class.java)

    init {
        MockitoAnnotations.initMocks(this)
        registerPresenter = RegisterPresenter(view, context)
    }

    @Test
    fun nullOnNameValueShouldCallErrorFunction() {
        val model = Register("", "awekwpefaowkfe@apoirgpao.com", "erijg"
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_empty_message)
    }

    @Test
    fun emailInvalidValueShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "awekwpefaowkfe", "erijg"
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_format_invalid)
    }

    @Test
    fun nullOnEmailValueShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "", "erijg"
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.email_empty_massage)
    }

    @Test
    fun nullOnPasswordValueShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "rpkaeprgokaprgok@iojaero.com", ""
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.password_empty_massage)
    }

    @Test
    fun nullOnRePasswordValueShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "rpkaeprgokaprgok@iojaero.com", "egrgr"
                , "", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.re_password_empty_massage)
    }

    @Test
    fun rePasswordNotEqualPasswordShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "rpkaeprgokaprgok@iojaero.com", "sssssss"
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.invalid_re_password)
    }

    @Test
    fun nameLongerThanConfigDefaultShouldCallErrorFunction() {
        val model = Register("apwjawjgeoa'pwokapworgke;orij;aowrijg;oaeirjgo;aierjg;oeairjgo;aeijgoa;eirjgi"
                , "rpkaeprgokaprgok@iojaero.com", "awej"
                , "awej", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.name_longer_that_default)
    }

    @Test
    fun passWordThanConfigDefaultShouldCallErrorFunction() {
        val model = Register("apwjawjgeoi", "rpkaeprgokaprgok@iojaero.com"
                , "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                , "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", "awej", bodyPart)
        registerPresenter.checkEdiText(model)
        verify(view, times(1)).onErrorMessage(R.string.password_longer_that_defaul)
    }
}