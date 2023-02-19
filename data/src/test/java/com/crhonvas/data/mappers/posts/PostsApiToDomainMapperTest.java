package com.crhonvas.data.mappers.posts;

import com.crhonvas.data.mappers.posts.PostsApiToDomainMapper;
import com.crhonvas.data.remote.model.posts.PostsApiResponse;
import com.crhonvas.domain.model.posts.Post;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for {@link PostsApiToDomainMapper}
 */
public class PostsApiToDomainMapperTest {
    private static final PostsApiResponse POST_API_RESPONSE_1 = new PostsApiResponse.Builder().id(22).build();
    private static final PostsApiResponse POST_API_RESPONSE_2 = new PostsApiResponse.Builder().id(23).build();
    private static final List<PostsApiResponse> POSTS_API_RESPONSE =
            Arrays.asList(POST_API_RESPONSE_1, POST_API_RESPONSE_2);

    private PostsApiToDomainMapper mapper;

    private static boolean compare(PostsApiResponse postsApiResponse, Post post) {
        return postsApiResponse.getUserId().equals(post.getUserId()) &&
                postsApiResponse.getId().equals(post.getId()) &&
                postsApiResponse.getTitle().equals(post.getTitle()) &&
                postsApiResponse.getBody().equals(post.getBody());
    }

    @Before
    public void setUp() throws Exception {
        mapper = new PostsApiToDomainMapper();
    }

    //region map Post
    @Test
    public void mapPost() throws Exception {
        Post actual = mapper.map(POST_API_RESPONSE_1);

        compare(POST_API_RESPONSE_1, actual);
    }

    @Test
    public void mapPost_emptyFields() throws Exception {
        PostsApiResponse expected = new PostsApiResponse.Builder().build();

        Post actual = mapper.map(expected);

        compare(expected, actual);
    }

    @Test
    public void mapPost_null_returnEmptyPost() throws Exception {
        PostsApiResponse input = null;

        Post expected = new Post.Builder().build();

        Post actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }
    //endregion

    //region map Posts
    @Test
    public void mapPosts() throws Exception {
        List<Post> actual = mapper.map(POSTS_API_RESPONSE);

        Assert.assertTrue(actual.size() == POSTS_API_RESPONSE.size());
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertTrue(compare(POSTS_API_RESPONSE.get(i), actual.get(i)));
        }
    }

    @Test
    public void mapPosts_emptyFields() throws Exception {
        List<PostsApiResponse> input =
                Arrays.asList(new PostsApiResponse.Builder().build(), new PostsApiResponse.Builder().build());

        List<Post> expected =
                Arrays.asList(new Post.Builder().build(), new Post.Builder().build());

        List<Post> actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void mapPosts_null_returnListOfEmptyPosts() throws Exception {
        List<PostsApiResponse> input = Arrays.asList(null, null);

        List<Post> expected = Arrays.asList(new Post.Builder().build(), new Post.Builder().build());

        List<Post> actual = mapper.map(input);

        Assert.assertTrue(expected.equals(actual));
    }
    //endregion
}