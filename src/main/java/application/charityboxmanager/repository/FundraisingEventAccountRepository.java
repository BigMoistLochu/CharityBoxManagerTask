package application.charityboxmanager.repository;

import application.charityboxmanager.model.FundraisingEventAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraisingEventAccountRepository extends JpaRepository<FundraisingEventAccount,Long> {


    @Query(value = """
        SELECT account.ID, 
               account.AMOUNT, 
               account.CURRENCY,
               account.EVENT_ID
        FROM fundraising_event_account as account
        INNER JOIN fundraising_event as event ON event.id = account.EVENT_ID
        INNER JOIN collection_box as box ON box.id = event.box_id
        WHERE box.id = :boxId
        """, nativeQuery = true)
    FundraisingEventAccount findAccountByCollectionBoxId(@Param("boxId") Long boxId);
}
