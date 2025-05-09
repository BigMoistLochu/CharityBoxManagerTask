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
}
