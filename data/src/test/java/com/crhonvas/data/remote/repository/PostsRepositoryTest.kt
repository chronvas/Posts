package com.crhonvas.data.remote.repository

import android.os.Build
import com.crhonvas.data.mappers.posts.PostsApiToDomainMapper
import com.crhonvas.data.remote.api.posts.IPostsApi
import com.crhonvas.data.remote.model.posts.PostsApiResponse
import com.crhonvas.data.utils.ListUtils.BuildVersionProvider
import com.crhonvas.data.utils.ListUtils.setBuildVersionProvider
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.posts.Post
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostsRepositoryTest {
    @Mock
    private val postsApi: IPostsApi? = null

    @Mock
    private val domainSchedulerProvider: IDomainSchedulerProvider? = null
    private val postsApiToDomainMapper = PostsApiToDomainMapper()
    private var postsRepository: PostsRepository? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setBuildVersionProvider(BuildVersionProvider { Build.VERSION_CODES.N })
        Mockito.`when`(domainSchedulerProvider!!.computation()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline())
        postsRepository = PostsRepository(postsApi!!, postsApiToDomainMapper, domainSchedulerProvider)
    }

    @get:Throws(Exception::class)
    @get:Test
    val post: Unit
        get() {
            Mockito.`when`(postsApi!!.getPost(2324)).thenReturn(
                Single.just(
                    PostsApiResponse.Builder().id(2324).build()
                )
            )
            val actual = postsRepository!!.getPost(2324)
            val expected = postsApiToDomainMapper.map(PostsApiResponse.Builder().id(2324).build())
            val testObserver = TestObserver<Post>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoErrors()
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val post_error: Unit
        get() {
            Mockito.`when`(postsApi!!.getPost(2324)).thenReturn(Single.error(Throwable()))
            val actual = postsRepository!!.getPost(2324)
            val testObserver = TestObserver<Post>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoValues()
            Assert.assertEquals(testObserver.errorCount().toLong(), 1)
        }

    @get:Throws(Exception::class)
    @get:Test
    val posts: Unit
        get() {
            Mockito.`when`(postsApi!!.posts)
                .thenReturn(Single.just(listOf(PostsApiResponse.Builder().id(23445).build())))
            val actual: Single<List<Post?>> = postsRepository!!.posts
            val expected = postsApiToDomainMapper.map(listOf(PostsApiResponse.Builder().id(23445).build()))
            val testObserver = TestObserver<List<Post?>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoErrors()
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val posts_error: Unit
        get() {
            Mockito.`when`(postsApi!!.posts).thenReturn(Single.error(Throwable()))
            val actual = postsRepository!!.posts
            val testObserver = TestObserver<List<Post>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoValues()
            Assert.assertEquals(testObserver.errorCount().toLong(), 1)
        }
}