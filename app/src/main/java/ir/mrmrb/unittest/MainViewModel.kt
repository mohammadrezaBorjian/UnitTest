package ir.mrmrb.unittest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var phoneValidator: PhoneValidator,
    var repository: Repository
    ):ViewModel() {
    var liveData = MutableLiveData<Boolean>()

    fun checkPhoneNumber(phone:String){
        if (phoneValidator.check(phone))
            validPhoneValidation(phone)
        else
            inValidPhoneValidation()
    }

    fun checkCallback(value:Boolean){
        if (value){
            repository.sendDataTosServer(object : Callback{
                override fun doAction(value: Boolean) {
                    liveData.postValue(value)
                }
            })
        }
    }

    private fun validPhoneValidation(phone: String){
        repository.sendDataToServer(phone)
    }

    private fun inValidPhoneValidation(){
        liveData.value =  false
    }

}