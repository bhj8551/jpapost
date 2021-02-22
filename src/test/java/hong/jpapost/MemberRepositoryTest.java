package hong.jpapost;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setUsername("member1");

        //when
        Long memberId = memberRepository.save(member);

        //then
        Assertions.assertThat(memberId).isEqualTo(member.getId());
    }

    @Test
    void find() {
        //given
        Member member = new Member();
        member.setUsername("member1");
        Long memeberId = memberRepository.save(member);

        //when
        Member findMember = memberRepository.find(memeberId);

        //then
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
}