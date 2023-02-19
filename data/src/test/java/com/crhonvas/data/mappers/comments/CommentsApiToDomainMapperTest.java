package com.crhonvas.data.mappers.comments;

import com.crhonvas.data.mappers.comments.CommentsApiToDomainMapper;
import com.crhonvas.data.remote.model.comments.CommentApiResponse;
import com.crhonvas.domain.model.comments.Comment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for {@link CommentsApiToDomainMapper}
 */
public class CommentsApiToDomainMapperTest {

    private static final CommentApiResponse COMMENT_API_RESPONSE_1 = new CommentApiResponse.Builder().id(11).build();
    private static final CommentApiResponse COMMENT_API_RESPONSE_2 = new CommentApiResponse.Builder().id(12).build();
    private static final List<CommentApiResponse> COMMENTS_API_RESPONSE =
            Arrays.asList(COMMENT_API_RESPONSE_1, COMMENT_API_RESPONSE_2);

    private CommentsApiToDomainMapper mapper;

    private static boolean compare(CommentApiResponse commentApiResponse, Comment comment) {
        return commentApiResponse.getBody().equals(comment.getBody()) &&
                commentApiResponse.getEmail().equals(comment.getEmail()) &&
                commentApiResponse.getId().equals(comment.getId()) &&
                commentApiResponse.getName().equals(comment.getName()) &&
                commentApiResponse.getPostId().equals(comment.getPostId());
    }

    @Before
    public void setUp() throws Exception {
        mapper = new CommentsApiToDomainMapper();
    }

    //region map Comment
    @Test
    public void mapComment() throws Exception {
        Comment actual = mapper.map(COMMENT_API_RESPONSE_1);

        compare(COMMENT_API_RESPONSE_1, actual);
    }

    @Test
    public void mapComment_emptyFields() throws Exception {
        CommentApiResponse expected = new CommentApiResponse.Builder().build();

        Comment actual = mapper.map(expected);

        compare(expected, actual);
    }

    @Test
    public void mapComment_null_returnEmptyComment() throws Exception {
        CommentApiResponse input = null;

        Comment expected = new Comment.Builder().build();

        Comment actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }
    //endregion

    //region map Comments
    @Test
    public void mapComments() throws Exception {
        List<Comment> actual = mapper.map(COMMENTS_API_RESPONSE);

        Assert.assertTrue(actual.size() == COMMENTS_API_RESPONSE.size());
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertTrue(compare(COMMENTS_API_RESPONSE.get(i), actual.get(i)));
        }
    }

    @Test
    public void mapComments_emptyFields() throws Exception {
        List<CommentApiResponse> input =
                Arrays.asList(new CommentApiResponse.Builder().build(), new CommentApiResponse.Builder().build());

        List<Comment> expected =
                Arrays.asList(new Comment.Builder().build(), new Comment.Builder().build());

        List<Comment> actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void mapComments_null_returnListOfEmptyComments() throws Exception {
        List<CommentApiResponse> input = Arrays.asList(null, null);

        List<Comment> expected = Arrays.asList(new Comment.Builder().build(), new Comment.Builder().build());

        List<Comment> actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }
    //endregion
}