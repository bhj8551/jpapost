package hong.jpapost.service;

import hong.jpapost.domain.Member;
import hong.jpapost.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");

        //when
        Long memberId = memberService.join(member);

        //then
        org.assertj.core.api.Assertions.assertThat(member).isSameAs(memberRepository.findOne(memberId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");
        memberService.join(member1);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(member2)
        );

        //then
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}