package hong.jpapost.domain;

import hong.jpapost.controller.PostForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime postDate;

    private int hit;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getPost().add(this);
    }


    //==생성 메서드==//
    public static Post createPost(Member member, PostForm form) {
        Post post = new Post();
        post.setMember(member);
        post.setBody(form.getBody());
        post.setTitle(form.getTitle());
        post.setPostDate(LocalDateTime.now());
        post.setHit(0);
        return post;
    }

    //==조회수 증가==//
    public void plusHit() {
        setHit(getHit() + 1);
    }

}
