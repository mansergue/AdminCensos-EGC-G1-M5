
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

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

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create(String username) {
		User result;
		UserAccount userAccount;
		Authority authority;
		Collection<Authority> authorities;

		result = new User();
		userAccount = new UserAccount();
		authorities = new ArrayList<Authority>();
		authority = new Authority();

		authority.setAuthority("USER");
		authorities.add(authority);
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);

		return result;
	}

	public User save(User user) {
		User result = userRepository.save(user);
		return result;
	}


	// Other business methods -------------------------------------------------
	
	public User findByPrincipal() {
		User result;

		result = new User();
		result = userRepository.findByPrincipal(LoginService.getPrincipal().getId());

		return result;
	}

	public User findByUsername(String username) {
		User result;

		result = new User();
		result = userRepository.findByUsername(username);

		return result;
	}

}

