package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", //중간테이블
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    //다대다 곤계(*:*)를 1:다, 다:1로 풀어내는 중간테이블이 있어야 한다.
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    //카테고리 자식은 여러개를 갖을 수 있음.
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();



}
