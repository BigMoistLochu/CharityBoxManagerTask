package application.charityboxmanager.repository;

import application.charityboxmanager.model.StoredMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StoredMoneyRepository extends JpaRepository<StoredMoney,Long> {

    List<StoredMoney> findAllByCollectionBoxId(Long idBox);

    @Modifying
    @Query("DELETE FROM StoredMoney sm WHERE sm.collectionBox.id = :boxId")
    void deleteAllStoredMoneyByCollectionBoxId(@Param("boxId") Long boxId);

    @Query(value = """
        SELECT SUM(AMOUNT)
        FROM STORED_MONEY
        WHERE BOX_ID = :boxId
        """, nativeQuery = true)
    BigDecimal getTotalStoredAmountByCollectionBoxId(@Param("boxId") Long boxId);

}
