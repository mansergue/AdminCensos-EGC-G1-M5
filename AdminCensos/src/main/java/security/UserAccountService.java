package security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;	
	
	// Supporting services ----------------------------------------------------
		
	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public UserAccount create(String authorityString){
		UserAccount result;
		Collection<Authority> authorities;
		Authority authority;
		
		result = new UserAccount();
		authorities = new ArrayList<Authority>();
		authority = new Authority();
		
		authority.setAuthority(authorityString);
		authorities.add(authority);
		result.setAuthorities(authorities);
		
		return result;
	}
	
	public UserAccount save(UserAccount userAccount){
		Assert.notNull(userAccount);
		UserAccount result = userAccountRepository.save(userAccount);
		return result;
	}
	
	
	// Other business methods -------------------------------------------------
	
	public UserAccount encode(UserAccount userAccount){
		String password;
		Md5PasswordEncoder passwordEncoder;
		passwordEncoder = new Md5PasswordEncoder();
		password = passwordEncoder.encodePassword(userAccount.getPassword(), null);
		userAccount.setPassword(password);
		return userAccount;
		
	}

}