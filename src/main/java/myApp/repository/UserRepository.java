package myApp.repository;

import myApp.model.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByUsername(String username);
}
