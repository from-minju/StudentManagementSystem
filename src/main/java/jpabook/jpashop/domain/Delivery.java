package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
    //EnumType.ORDINAL 로 쓰면 READY, COMP가 1,2이런식으로 숫자로 인식됨. 단점은 중간에 뭐가 추가되면 숫자가 다 바뀜. 망함.

}
