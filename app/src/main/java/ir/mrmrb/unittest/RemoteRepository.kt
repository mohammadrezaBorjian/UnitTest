package ir.mrmrb.unittest

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    var retrofit: Retrofit
) {
    fun sendDataToServer(phone:String):Boolean{
        return true
    }
}