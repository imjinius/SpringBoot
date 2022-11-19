package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 클래스 내의 모든 테스트 메서드들을 돌릴 수 있는데 이 때 순서대로 실행 되지 않을 수도 있다.
    // 각 테스트는 순서에 영향이 없어야 한다. 즉, 서로 의존 관계가 없어야 한다.
    // AfterEach는 각 테스트 케이스가 끝날때마다 실행되는 메서드를 뜻한다.
    // 여기서는 이미 저장된 데이터가 영향을 주고 있어서 객체를 비우는 clearStore();라는 메서드를 레파지토리에 작성하였고 이를 실행.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(member,null);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
