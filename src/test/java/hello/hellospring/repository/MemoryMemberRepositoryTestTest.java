package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTestTest {

    public MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void save() {
        Member member = new Member();
        member.setName("jinius");
        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);

    }


    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("jini1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("jini2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("jini1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    void findAll() {
    }
}