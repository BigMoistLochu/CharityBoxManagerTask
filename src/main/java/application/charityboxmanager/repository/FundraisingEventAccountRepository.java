package application.charityboxmanager.repository;

import application.charityboxmanager.model.FundraisingEventAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraisingEventAccountRepository extends JpaRepository<FundraisingEventAccount,Long> {
}
