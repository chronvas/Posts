package com.crhonvas.data.remote.repository

import android.os.Build
import com.crhonvas.data.mappers.comments.CommentsApiToDomainMapper
import com.crhonvas.data.remote.api.comments.ICommentsApi
import com.crhonvas.data.remote.model.comments.CommentApiResponse
import com.crhonvas.data.utils.ListUtils.BuildVersionProvider
import com.crhonvas.data.utils.ListUtils.setBuildVersionProvider
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.comments.Comment
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CommentsRepositoryTest {
    @Mock
    var commentsApi: ICommentsApi? = null

    @Mock
    private val domainSchedulerProvider: IDomainSchedulerProvider? = null
    private var commentsRepository: CommentsRepository? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setBuildVersionProvider(BuildVersionProvider { Build.VERSION_CODES.N })
        Mockito.`when`(domainSchedulerProvider!!.computation()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.io()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(domainSchedulerProvider.ui()).thenReturn(Schedulers.trampoline())
        commentsRepository = CommentsRepository(commentsApi!!, commentsApiToDomainMapper, domainSchedulerProvider)
    }

    @get:Throws(Exception::class)
    @get:Test
    val commentById: Unit
        get() {
            val input = CommentApiResponse.Builder().id(242).build()
            Mockito.`when`(commentsApi!!.getCommentForCommentId(242)).thenReturn(Single.just(input))
            val actual = commentsRepository!!.getCommentById(242)
            val expected = commentsApiToDomainMapper.map(input)
            val testObserver = TestObserver<Comment>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoErrors()
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val commentById_error: Unit
        get() {
            Mockito.`when`(commentsApi!!.getCommentForCommentId(222)).thenReturn(Single.error(Throwable()))
            val actual = commentsRepository!!.getCommentById(222)
            val testObserver = TestObserver<Comment>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            Assert.assertEquals(testObserver.valueCount().toLong(), 0)
            testObserver.assertNoValues()
        }

    @get:Throws(Exception::class)
    @get:Test
    val comments: Unit
        get() {
            val input: List<CommentApiResponse?> = listOf(CommentApiResponse.Builder().id(242).build())
            Mockito.`when`(commentsApi!!.comments).thenReturn(Single.just(input))
            val actual: Single<List<Comment?>> = commentsRepository!!.comments
            val expected = commentsApiToDomainMapper.map(input)
            val testObserver = TestObserver<List<Comment?>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoErrors()
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val comments_error: Unit
        get() {
            Mockito.`when`(commentsApi!!.comments).thenReturn(Single.error(Throwable()))
            val actual = commentsRepository!!.comments
            val testObserver = TestObserver<List<Comment>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            Assert.assertEquals(testObserver.valueCount().toLong(), 0)
            testObserver.assertNoValues()
        }

    @get:Throws(Exception::class)
    @get:Test
    val commentsForPostId: Unit
        get() {
            val input: List<CommentApiResponse?> = listOf(CommentApiResponse.Builder().id(242).build())
            Mockito.`when`(commentsApi!!.getCommentsForPostId(242)).thenReturn(Single.just(input))
            val actual: Single<List<Comment?>> = commentsRepository!!.getCommentsForPostId(242)
            val expected = commentsApiToDomainMapper.map(input)
            val testObserver = TestObserver<List<Comment?>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            testObserver.assertNoErrors()
            testObserver.assertValue(expected)
        }

    @get:Throws(Exception::class)
    @get:Test
    val commentsForPostId_error: Unit
        get() {
            Mockito.`when`(commentsApi!!.getCommentsForPostId(242)).thenReturn(Single.error(Throwable()))
            val actual = commentsRepository!!.getCommentsForPostId(242)
            val testObserver = TestObserver<List<Comment>>()
            actual.subscribe(testObserver)
            testObserver.assertSubscribed()
            Assert.assertEquals(testObserver.valueCount().toLong(), 0)
            testObserver.assertNoValues()
        }

    companion object {
        private val commentsApiToDomainMapper = CommentsApiToDomainMapper()
    }
}