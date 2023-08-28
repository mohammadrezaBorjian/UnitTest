package ir.mrmrb.unittest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    var remoteRepository: RemoteRepository
) {

    fun sendDataToServer(phone:String):Flow<Boolean>{
        return flow {
            emit(remoteRepository.sendDataToServer(phone))
        }
    }
    fun sendDataTosServer(callback: Callback?){
        callback?.doAction(true)
    }
}