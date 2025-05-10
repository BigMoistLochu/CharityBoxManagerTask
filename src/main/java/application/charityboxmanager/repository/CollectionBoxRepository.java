package application.charityboxmanager.repository;

import application.charityboxmanager.model.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxRepository extends JpaRepository<CollectionBox,Long> {
}
