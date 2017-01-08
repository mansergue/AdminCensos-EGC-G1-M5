
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.User;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create(String username) {
		User user= new User();
		UserAccount userAccount= new UserAccount();
		Authority authority= new Authority();
		Collection<Authority> authorities= new ArrayList<Authority>();

		authority.setAuthority("USER");
		authorities.add(authority);
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.setAuthorities(authorities);

		user.setUserAccount(userAccount);

		return user;
	}


	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
	

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	// Other business methods -------------------------------------------------
	public User findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		User result;
		result = userRepository.findByUserAccount(userAccount);
		return result;
	}
	
	public User findByPrincipal() {
		User result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public User findByUsername(String username) {
		User user;

		user = new User();
		user = userRepository.findByUsername(username);
		Assert.notNull(user);
		
		return user;
	}
	
}

