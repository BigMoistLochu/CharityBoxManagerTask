package application.charityboxmanager.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class CollectionBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isEmpty;

    private boolean isAssigned;

    @OneToMany(mappedBy = "collectionBox")
    private Set<StoredMoney> storedMoneyList;

    public CollectionBox(){}

    public CollectionBox(boolean isEmpty, boolean isAssigned) {
        this.isEmpty = isEmpty;
        this.isAssigned = isAssigned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public Set<StoredMoney> getStoredMoneyList() {
        return storedMoneyList;
    }

    public void setStoredMoneyList(Set<StoredMoney> storedMoneyList) {
        this.storedMoneyList = storedMoneyList;
    }
}
