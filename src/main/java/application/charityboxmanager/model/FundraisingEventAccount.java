package application.charityboxmanager.model;

import application.charityboxmanager.model.common.Money;
import jakarta.persistence.*;

@Entity
public class FundraisingEventAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private FundraisingEvent fundraisingEvent;

    @Embedded
    private Money money;

    public FundraisingEventAccount() {}

    public FundraisingEventAccount(FundraisingEvent fundraisingEvent, Money money) {
        this.fundraisingEvent = fundraisingEvent;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FundraisingEvent getFundraisingEvent() {
        return fundraisingEvent;
    }

    public void setFundraisingEvent(FundraisingEvent fundraisingEvent) {
        this.fundraisingEvent = fundraisingEvent;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
