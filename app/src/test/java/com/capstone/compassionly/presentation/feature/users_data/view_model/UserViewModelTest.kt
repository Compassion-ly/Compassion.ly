package com.capstone.compassionly.presentation.feature.users_data.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.capstone.compassionly.DataDummy
import com.capstone.compassionly.UtilsTest.getOrAwaitValue
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.SchoolRepository
import com.capstone.compassionly.repository.core.network.UserRepository
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

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var schoolRepository: SchoolRepository

    @Mock
    private lateinit var stateAppPreference: StateAppPreference

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var userViewModel: UserViewModel

    private val userDummy = DataDummy.generateDummyGetUser()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userViewModel = UserViewModel(userRepository, schoolRepository, stateAppPreference, localDataSource)
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
    fun `when got a data of Detail User must be Loading First`() = runTest {
        // Given
        `when`(userRepository.getMe("s")).thenReturn(Response.success(SuccessResponse(userDummy)))

        // Observer to observe changes in LiveData
        val observer = Observer<Resources<Any?>> {}
        try {
            userViewModel.getMe("s").observeForever(observer)

            // When
            val actualResult = userViewModel.getMe("s").getOrAwaitValue()

            // Then
            assertEquals(Resources.Loading, actualResult)
        } finally {
            userViewModel.getMe("s").removeObserver(observer)
        }
    }

    @Test
    fun `when got a data of Detail User error`() = runTest {
        // Given
        val errorResponse = Response.error<SuccessResponse<DetailUserModel>>(
            404,
            "User not found".toResponseBody("text/plain".toMediaTypeOrNull())
        )
        `when`(userRepository.getMe("s")).thenReturn(errorResponse)


        // When
        val actualResult = userRepository.getMe("s")

        // Then
        assertEquals(errorResponse.code(), actualResult.code())
    }

    @Test
    fun `when got a data of Detail User success`() = runTest {
        // Check API
        `when`(userRepository.getMe("s")).thenReturn(Response.success(SuccessResponse(userDummy)))

        // Get API
        val actualResult = userRepository.getMe("s")

        // Then
        assertEquals(Response.success(userDummy).code() , actualResult.code())
    }
}