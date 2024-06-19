package com.capstone.compassionly.presentation.feature.quickrec.viewmodel

import androidx.annotation.OptIn
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.capstone.compassionly.UtilsTest.getOrAwaitValue
import com.capstone.compassionly.models.RatingModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.forsending.Data
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.repository.core.network.QuickRecRepository
import com.capstone.compassionly.utility.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@kotlin.OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class QuickRecViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var quickRecRepository: QuickRecRepository

    private lateinit var quickRecViewModel: QuickRecViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        quickRecViewModel = QuickRecViewModel(quickRecRepository)
    }

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get quick recommendation non null and return success` () = runTest {
        val data = MutableLiveData<Resources<Any?>> ()
        data.value = Resources.Success(QuickRecResponse(Data(listOf("ada"), listOf("ada"), listOf("ada"),listOf("ada") ), "berhasil"))
        `when`(quickRecRepository.sendUserDesc("token", "sdfsdfsdfsdfdsfsf")).thenReturn(data)

        val actual = quickRecViewModel.sendUserDesc("token", "sdfsdfsdfsdfdsfsf").getOrAwaitValue()
        assertNotNull(actual)
        assertEquals(data.value, actual)
    }

    @Test
    fun `get quick recommendation non null and return fail` () = runTest {

        val data = MutableLiveData<Resources<Any?>> ()
        data.value = Resources.Error("Gagal load")
        `when`(quickRecRepository.sendUserDesc("token", "sdfsdfsdfsdfdsfsf")).thenReturn(data)

        val actual = quickRecViewModel.sendUserDesc("token", "sdfsdfsdfsdfdsfsf").getOrAwaitValue()
        assertNotNull(actual)
        assertEquals(data.value, actual)
    }
}