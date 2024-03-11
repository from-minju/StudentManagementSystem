package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //@Repository안에 @Component -> 컴포넌트 스캔의 대상이다.
public class MemberRepository {

    @PersistenceContext // JPA의 EntityManager를 주입해준다.
    private EntityManager em;

    public void save(Member member) {
        em.persist(member); //persist() : Member엔티티를 넣음. 영속성 컨텍스트에 Member객체를 올림.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); //find() : 단건조회, 파라미터:(타입, PK)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",
                Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
