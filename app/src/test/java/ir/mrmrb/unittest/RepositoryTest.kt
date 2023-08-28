package ir.mrmrb.unittest

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class RepositoryTest() {
    lateinit var repository: Repository
    @Mock lateinit var remoteRepository: RemoteRepository
    var validPhone = "09195881482"
    var inValidPhone = "28418859190"

    @Before
    fun init(){
        repository = Repository(remoteRepository)
        `when`(remoteRepository.sendDataToServer(validPhone)).thenReturn(true)
    }
    @Test
    fun `send data to server success result`(){
        runTest {
            val result = repository.sendDataToServer(validPhone)
            Assert.assertFalse(result.single())
        }
    }
}