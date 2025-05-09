package application.charityboxmanager.model;

import jakarta.persistence.*;

@Entity
public class CollectionBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "collectionBox")
    private FundraisingEvent event;

    public CollectionBox(){}

}
