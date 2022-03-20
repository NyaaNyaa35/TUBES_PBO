package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ControllerPostTest {
    @Test
    void getAllPostTest(){
        var conPost = new ControllerPost();
        assertNotNull(conPost.getAllPost());
    }

    @Test
    void updateLikePostTest(){
        var conPost = new ControllerPost();
        var postLikeUpdated = conPost.getPost(8);

        postLikeUpdated.setJumlahLike(4);
        assertNotNull(conPost.updateLikePost(postLikeUpdated,8));
    }
}