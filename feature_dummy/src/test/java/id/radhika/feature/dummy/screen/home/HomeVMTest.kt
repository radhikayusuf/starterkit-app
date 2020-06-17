package id.radhika.feature.dummy.screen.home

import id.radhika.feature.dummy.data.usecases.HomeUseCase
import id.radhika.lib.data.model.ApiResult
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 09/Apr/2020
 */
class HomeVMTest {

    private val homeUseCase: HomeUseCase = mockk()
    private var homeDao = HomeDao()

    private val homeVM = HomeVM(homeUseCase)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `check fetch data should be success`() = runBlocking {
        // Given
        coEvery { homeUseCase.getHomeData() } returns ApiResult(true, "lalala")

        // When
        homeVM.onCreate()

        // Then
        assertEquals(homeDao.name, "lalala")
        verify(inverse = true) { homeVM.showToast(message = any()) }
    }
}