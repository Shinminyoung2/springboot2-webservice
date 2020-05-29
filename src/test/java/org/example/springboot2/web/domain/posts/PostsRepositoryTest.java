package org.example.springboot2.web.domain.posts;

import org.example.springboot2.domain.posts.Posts;
import org.example.springboot2.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //자동으로 H2 데이터베이스 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "test";
        String content = "content";

        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("나")
                                    .build());

        //when
        List<Posts> posts = postsRepository.findAll();

        //then
        Posts post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
}
