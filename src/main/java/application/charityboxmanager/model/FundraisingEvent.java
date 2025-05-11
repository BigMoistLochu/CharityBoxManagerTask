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

    public FundraisingEvent(String name){
        this.name = name;
    }

    public FundraisingEvent(String name, CollectionBox collectionBox) {
        this.name = name;
        this.collectionBox = collectionBox;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionBox getCollectionBox() {
        return collectionBox;
    }

    public void setCollectionBox(CollectionBox collectionBox) {
        this.collectionBox = collectionBox;
    }
}
