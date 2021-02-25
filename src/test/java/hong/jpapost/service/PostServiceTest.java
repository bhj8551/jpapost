package hong.jpapost.service;

import hong.jpapost.controller.PostForm;
import hong.jpapost.domain.Member;
import hong.jpapost.domain.Post;
import hong.jpapost.repository.MemberRepository;
import hong.jpapost.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    @Test
    public void 게시물_생성() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");
        memberService.join(member);
        PostForm postForm = new PostForm();

        Post post = Post.createPost(member, postForm);

        //when
        Long postId = postService.post(post);

        //then
        Assertions.assertThat(post).isSameAs(postService.findOne(postId));
        Assertions.assertThat(member).isSameAs(postService.findOne(postId).getMember());
        Assertions.assertThat(post.getHit()).isEqualTo(0);
    }

    @Test
    public void 히트수_증가() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");
        memberService.join(member);
        PostForm postForm = new PostForm();

        Post post = Post.createPost(member, postForm);
        postService.post(post);
        //when
        post.plusHit();

        //then
        Assertions.assertThat(post.getHit()).isEqualTo(1);
    }
}