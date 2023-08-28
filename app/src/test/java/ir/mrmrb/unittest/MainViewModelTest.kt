package ir.mrmrb.unittest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock lateinit var phoneValidator: PhoneValidator
    @Mock lateinit var repository: Repository
    lateinit var mainViewModel: MainViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    var validPhone = "09195881482"
    var inValidPhone = "28418859190"

    @Before
    fun initVariable(){
        mainViewModel = MainViewModel(phoneValidator,repository)
        `when`(phoneValidator.check(validPhone)).thenReturn(true)
        `when`(phoneValidator.check(inValidPhone)).thenReturn(false)
        `when`(repository.sendDataTosServer(any())).thenAnswer {
            val callback: Callback = it.arguments[0] as Callback
            callback.doAction(true)
        }
    }

    @Test
    fun `valid phone number test`(){
        mainViewModel.checkPhoneNumber(validPhone)
        verify(repository, atLeastOnce()).sendDataToServer(anyString())
    }

    @Test
    fun `invalid phone number test`(){
        mainViewModel.checkPhoneNumber(inValidPhone)
        val res = mainViewModel.liveData.getOrAwaitValueTest()
        Assert.assertFalse(res)
    }

    @Test
    fun `check callback`(){
        mainViewModel.checkCallback(true)
        val res = mainViewModel.liveData.getOrAwaitValueTest()
        Assert.assertTrue(res)
    }
}