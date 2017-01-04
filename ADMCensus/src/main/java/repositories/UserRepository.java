package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userAccount.id= ?1")
	User findByPrincipal(int id);

	@Query("select u from User u where u.userAccount.username= ?1")
  User findByUsername(String username);

}
