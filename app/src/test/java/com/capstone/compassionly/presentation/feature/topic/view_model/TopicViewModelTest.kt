package com.capstone.compassionly.presentation.feature.topic.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.capstone.compassionly.DataDummy
import com.capstone.compassionly.UtilsTest.getOrAwaitValue
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.RatingModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.forsending.RatingModelSend
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.TopicRepository
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class TopicViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var topicRepository: TopicRepository

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var stateAppPreference: StateAppPreference


    private lateinit var topicViewModel: TopicViewModel


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        topicViewModel = TopicViewModel(localDataSource, topicRepository, stateAppPreference)
    }

    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `postRate success`() = runTest {
        val data = RatingModel(1, 1, 1, 1)
        Mockito.`when`(topicRepository.postRate("ssss", RatingModelSend(1, 1))).thenReturn(
            Response.success(
                SuccessResponse(data)
            )
        )

        topicViewModel.postToken("ssss", 1, 1)
        val actual = topicViewModel.resultPost.getOrAwaitValue()
        assertEquals(Resources.Success::class, actual::class)
    }

    @Test
    fun `postRate failed`() = runTest {
        val errorResponse = Response.error<SuccessResponse<RatingModel>>(
            500,
            "User not found".toResponseBody("text/plain".toMediaTypeOrNull())
        )
        Mockito.`when`(topicRepository.postRate("ssss", RatingModelSend(1, 1))).thenReturn(
            errorResponse
        )

        topicViewModel.postToken("ssss", 1, 1)
        val actual = topicViewModel.resultPost.getOrAwaitValue()
        assertEquals(Resources.Error::class, actual::class)
    }

    @Test
    fun `getTopic non null`() = runTest {
        val data  = DataDummy.getListTopic()
        Mockito.`when`(topicRepository.getAllTopic("ss", 25, 1)).thenReturn(Response.success(
            SuccessResponse(data, "berhasil")
        ))

        val actual = topicViewModel.getTopic("ss", 25, 1)
        val value = actual.getOrAwaitValue()
        assertNotNull(value)
    }

}