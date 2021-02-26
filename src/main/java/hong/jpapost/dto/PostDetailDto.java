package hong.jpapost.dto;

import hong.jpapost.domain.Member;
import hong.jpapost.domain.Post;
import lombok.Getter;
import lombok.Setter;
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
