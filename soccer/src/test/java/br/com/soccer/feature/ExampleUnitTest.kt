package br.com.soccer.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.soccer.domain.soccer.Classification
import br.com.soccer.domain.soccer.Score
import br.com.soccer.feature.home.HomeViewModel
import br.com.soccer.repositorie.soccer.FakeSoccerRepositoryImpl
import br.com.soccer.repositorie.soccer.SoccerRepository
import br.com.soccer.repositorie.soccer.SoccerRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkConstructor
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import org.junit.rules.TestRule
import org.mockito.Spy


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    @Spy

//    @Mock
//    lateinit var score: Score

    private val mainThreadSurrogate = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

//    @get:Rule
//    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun addition_isCorrect() {
        mainThreadSurrogate.runBlockingTest {
//            var repository: SoccerRepository = SoccerRepositoryImpl

            val homeViewModel = HomeViewModel(FakeSoccerRepositoryImpl())
//            Mockito.`when`( repository.getScore().table ).then(Answer { score.table })
            val observer = mock(Observer::class.java) as Observer<List<Classification>>
            homeViewModel.itemsLiveData.observeForever(observer)
//
            homeViewModel.loadScore()

            delay(5000)
            println(homeViewModel.itemsLiveData.value)
            assert(homeViewModel.itemsLiveData.value?.size == 15)
//            assertTrue(1 == 1)
        }

    }
}
