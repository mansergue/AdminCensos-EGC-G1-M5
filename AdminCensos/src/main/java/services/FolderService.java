package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import security.LoginService;
import security.UserAccount;

import domain.Folder;
import domain.Message;
import domain.User;

@Service
@Transactional
public class FolderService {

	// Managed Repository
	@Autowired
	private FolderRepository folderRepository;

	// Supporting Services
	@Autowired
	private UserService userService;

	// Constructor
	public FolderService() {
		super();
	}

	// Simple CRUD methods
	public Folder create(User user) {
		Folder result;
		result = new Folder();
		Collection<Message> messages = new HashSet<Message>();
		result.setMessages(messages);
		result.setUser(user);
		return result;
	}

	public Collection<Folder> findAll() {
		Collection<Folder> result;
		result = folderRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Folder findOne(int folderId) {
		Folder result;
		result = folderRepository.findOne(folderId);
		Assert.notNull(result);
		return result;
	}

	public Folder save(Folder folder) {
		Assert.notNull(folder);
		Assert.notNull(folder.getMessages());
		Assert.hasLength(folder.getName());
		Assert.isTrue(checkPrincipalIsOwner(folder));
		Folder result;
		User user = folder.getUser();
		
		Assert.isTrue(getInbox(user.getUserAccount()).getId() != folder
				.getId());
		Assert.isTrue(getOutbox(user.getUserAccount()).getId() != folder
				.getId());
		Assert.isTrue(getTrash(user.getUserAccount()).getId() != folder
				.getId());
		
		Assert.isTrue(!folder.getName().equals("inbox"));
		Assert.isTrue(!folder.getName().equals("outbox"));
		Assert.isTrue(!folder.getName().equals("trashbox"));
		Assert.isTrue(!folder.getName().equals("spambox"));
		
		result = folderRepository.save(folder);
		user.getFolders().add(result);
		userService.save(user);
		return result;
	}

	public void delete(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(checkPrincipalIsOwner(folder));
		Assert.isTrue(folder.getId() != 0);
		User user = folder.getUser();

		Assert.isTrue(getInbox(user.getUserAccount()).getId() != folder
				.getId());
		Assert.isTrue(getOutbox(user.getUserAccount()).getId() != folder
				.getId());
		Assert.isTrue(getTrash(user.getUserAccount()).getId() != folder
				.getId());

		user.getFolders().remove(folder);
		folderRepository.delete(folder);
		userService.save(user);
	}

	// Other business methods
	
	public Collection<Integer> foldersIdForActor(User user) {
		Collection<Integer> result = new HashSet<Integer>();
		result = folderRepository.foldersIdByUser(user.getId());
		return result;
	}

	public boolean checkPrincipalIsOwner(Folder folder) {
		boolean result = false;
		UserAccount principalUserAccount;
		UserAccount actorUserAccount;
		User user;

		principalUserAccount = LoginService.getPrincipal();
		user = folder.getUser();
		actorUserAccount = user.getUserAccount();

		Assert.isTrue(principalUserAccount.equals(actorUserAccount));

		result = true;
		return result;
	}


	public Folder getInbox(UserAccount userAccount) {

		Assert.notNull(userAccount);
		User user = userService.findByUserAccount(userAccount);
		Folder result = new Folder();
		Collection<Folder> folders = user.getFolders();
		for (Folder f : folders) {
			if (f.getName().equals("Issues Received")) {
				result = f;
				break;
			}
		}

		return result;
	}

	public Folder getOutbox(UserAccount userAccount) {

		Assert.notNull(userAccount);

		User user = userService.findByUserAccount(userAccount);
		Folder result = new Folder();
		Collection<Folder> folders = user.getFolders();
		for (Folder f : folders) {
			if (f.getName().equals("Issues Sent")) {
				result = f;
				break;
			}
		}

		return result;
	}

	public Folder getTrash(UserAccount userAccount) {

		Assert.notNull(userAccount);

		User user = userService.findByUserAccount(userAccount);
		Folder result = new Folder();
		Collection<Folder> folders = user.getFolders();
		for (Folder f : folders) {
			if (f.getName().equals("Issues Trashbox")) {
				result = f;
				break;
			}
		}

		return result;
	}


	public Collection<String> foldersNameByActor(int userId) {
		Collection<String> result;
		result = folderRepository.foldersNameByUser(userId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Integer> foldersIdByActor(int userId) {
		Collection<Integer> result;
		result = folderRepository.foldersIdByUser(userId);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Folder> defaultFolders(User user) {

		Collection<Folder> result = new HashSet<Folder>();

		Folder issuesReceived = create(user);
		Folder issuesSent = create(user);
		Folder trashbox = create(user);

		issuesReceived.setName("Issues Received");
		issuesSent.setName("Issues Sent");
		trashbox.setName("Issues Trashbox");

		Folder inboxNew = folderRepository.save(issuesReceived);
		Folder outboxNew = folderRepository.save(issuesSent);
		Folder trashboxNew = folderRepository.save(trashbox);

		result.add(outboxNew);
		result.add(inboxNew);
		result.add(trashboxNew);

		return result;
	}


}
