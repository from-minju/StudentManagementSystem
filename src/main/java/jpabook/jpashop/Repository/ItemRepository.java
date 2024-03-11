package jpabook.jpashop.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {//id값이 없다==완전히 새로 생성한 객체라는 뜻
            em.persist(item);
        } else { //id값이 있다==이미 db에 등록되어있다는 뜻
            em.merge(item); //업데이트 느낌?
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
