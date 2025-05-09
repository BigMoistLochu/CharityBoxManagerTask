package application.charityboxmanager.model;

import jakarta.persistence.*;

@Entity
public class FundraisingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private CollectionBox collectionBox;

    public FundraisingEvent(){}

}
