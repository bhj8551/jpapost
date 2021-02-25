package hong.jpapost.domain;

import hong.jpapost.controller.MemberForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> post = new ArrayList<>();

    //==생성 메서드==//

    public static Member createMember(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());

        return member;
    }
}
