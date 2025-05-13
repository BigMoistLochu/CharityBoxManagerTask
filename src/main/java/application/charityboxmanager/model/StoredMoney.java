package application.charityboxmanager.model;

import application.charityboxmanager.model.common.Money;
import jakarta.persistence.*;

@Entity
public class StoredMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private CollectionBox collectionBox;

    @Embedded
    private Money money;

    public StoredMoney(){}

    public StoredMoney(CollectionBox collectionBox, Money money) {
        this.collectionBox = collectionBox;
        this.money = money;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CollectionBox getCollectionBox() {
        return collectionBox;
    }

    public void setCollectionBox(CollectionBox collectionBox) {
        this.collectionBox = collectionBox;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
