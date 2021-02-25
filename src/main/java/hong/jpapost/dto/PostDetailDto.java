package hong.jpapost.dto;

import hong.jpapost.controller.PostForm;
import hong.jpapost.domain.Member;
import hong.jpapost.domain.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter @Setter
public class PostDetailDto {

    private Member member;

    private String title;

    private String body;

    private LocalDateTime postDate;

    private int hit;

    public static PostDetailDto createPostDetailDto(Post post) {
        PostDetailDto pdd = new PostDetailDto();

        pdd.setTitle(post.getTitle());
        pdd.setBody(post.getBody());
        pdd.setMember(post.getMember());
        pdd.setPostDate(post.getPostDate());
        pdd.setHit(post.getHit());
        return pdd;
    }
}
