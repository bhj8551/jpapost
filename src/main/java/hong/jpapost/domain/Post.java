package hong.jpapost.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
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

    public static Post createPost(Member member) {
        Post post = new Post();
        post.setMember(member);
        post.setHit(0);
        return post;
    }

    //==조회수 증가==//
    public void plusHit() {
        setHit(getHit() + 1);
    }

}
