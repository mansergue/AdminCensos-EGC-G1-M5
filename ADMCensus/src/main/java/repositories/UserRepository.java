package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;
import security.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.username= ?1")
	User findByUsername(String username);
	
	@Query("select u from User u where u.userAccount = ?1")
	public User findByUserAccount(UserAccount userAccount);

}
