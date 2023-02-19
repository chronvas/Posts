package com.crhonvas.data.remote.repository

import com.crhonvas.data.mappers.user.ApiAddressToDomainMapper
import com.crhonvas.data.mappers.user.ApiCompanyToDomainMapper
import com.crhonvas.data.mappers.user.ApiGeoToDomainMapper
import com.crhonvas.data.mappers.user.UserApiToDomainMapper
import com.crhonvas.data.remote.api.users.IUserApi
import com.crhonvas.data.remote.model.user.UserApiResponse
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.user.User
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserRepositoryTest {
    @Mock
    private val userApi: IUserApi? = null

    @Mock
    private val domainSchedulerProvider: IDomainSchedulerProvider? = null
    private var userRepository: UserRepository? = null
    private val apiGeoToDomainMapper = ApiGeoToDomainMapper()
    private val apiAddressToDomainMapper = ApiAddressToDomainMapper(apiGeoToDomainMapper)
    private val apiCompanyToDomainMapper = ApiCompanyToDomainMapper()
    private val userApiToDomainMapper = UserApiToDomainMapper(apiAddressToDomainMapper, apiCompanyToDomainMapper)
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(domainSchedulerProvider!!.computation()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline())
        userRepository = UserRepository(userApi!!, userApiToDomainMapper, domainSchedulerProvider)
    }

    @get:Throws(Exception::class)
    @get:Test
    val user: Unit
        get() {
            val input = UserApiResponse.Builder().id(4845).build()
            Mockito.`when`(userApi!!.getUser(4845)).thenReturn(Single.just(input))
            val actual = userRepository!!.getUser(4845)
            val expected = userApiToDomainMapper.map(input)
            val testObserver = TestObserver<User>()
            actual.subscribe(testObserver)
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val user_Error: Unit
        get() {
            Mockito.`when`(userApi!!.getUser(4845)).thenReturn(Single.error(Throwable("error")))
            val actual = userRepository!!.getUser(4845)
            val testObserver = TestObserver<User>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoValues()
            Assert.assertEquals(testObserver.errorCount().toLong(), 1)
        }
}