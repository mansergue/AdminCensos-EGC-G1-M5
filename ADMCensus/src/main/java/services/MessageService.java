package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;

import domain.Folder;
import domain.Message;
import domain.User;

@Service
@Transactional
public class MessageService {

	// Managed Repository
	@Autowired
	private MessageRepository messageRepository;

	// Supporting Services
	@Autowired
	private FolderService folderService;

	@Autowired
	private UserService userService;

	// Constructor
	public MessageService() {
		super();
	}

	// Simple CRUD methods
	public Message create() {
		Message result;
		result = new Message();
		User sender = userService.findByPrincipal();
		Folder outbox = folderService.getOutbox(sender.getUserAccount());
		result.setSender(sender);
		result.setFolder(outbox);
		result.setMoment(new Date(System.currentTimeMillis()));
		User admin = userService.findByUsername("admin");
		result.setRecipient(admin);
		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;
		result = messageRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Message findOne(int messageId) {
		Message result;
		result = messageRepository.findOne(messageId);
		Assert.notNull(result);
		return result;
	}

	public Message save(Message message) {
		Assert.notNull(message);
		Assert.notNull(message.getRecipient());
		Message result;
		result = messageRepository.save(message);
		return result;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		User user = userService.findByPrincipal();
		System.out.println(folderService.getTrash(user.getUserAccount()).getName());
		if (folderService.getTrash(user.getUserAccount()) == (message.getFolder())) {
			folderService.getTrash(user.getUserAccount()).getMessages().remove(message);
			messageRepository.delete(message);
		} else {
			moveToTrash(message);
		}
	}

	// Other business methods
	
	public void checkedMessage(Message message){
		Assert.notNull(message);
		Assert.isTrue(checkPrincipalIsRecipient(message));
		
		message.setChecked(true);
		moveToTrash(message);
		
		Collection<Message> messages = findAll();
		for(Message m: messages){
			if(m.getSubject().equals(message.getSubject())&&m.getBody().equals(message.getBody())
					&&m.getSender().equals(message.getSender())
					&&m.getRecipient().equals(message.getRecipient())
					&&m.getId()!=message.getId()){
				m.setChecked(true);
				save(m);
			}
		}
	}

	public void sendMessage(Message message) {
		Assert.notNull(message);
		Assert.isTrue(checkPrincipalIsSender(message) || checkPrincipalIsRecipient(message));

		Folder inbox = folderService.getInbox(message.getRecipient().getUserAccount());
		
		Message copyMessage = create();
		save(message);
		
		copyMessage.setSender(message.getSender());
		copyMessage.setMoment(message.getMoment());
		copyMessage.setRecipient(message.getRecipient());
		copyMessage.setSubject(message.getSubject());
		copyMessage.setBody(message.getBody());
		copyMessage.setFolder(inbox);
		save(copyMessage);
	}

//	public void sendMessage(Message message) {
//		Assert.notNull(message);
//		Assert.isTrue(checkPrincipalIsSender(message) || checkPrincipalIsRecipient(message));
//		
//		
//		Collection<User> users = userService.findAll();
//		for(User u: users){
//			if(!u.getUserAccount().getAuthorities().equals("ADMIN")){
//				users.remove(u);
//			}
//		}
//		users.remove(message.getSender());
//		for(User u: users){
//			Folder inbox = folderService.getInbox(u.getUserAccount());
//			Message copyMessage = create();
//			copyMessage.setSender(message.getSender());
//			copyMessage.setMoment(message.getMoment());
//			copyMessage.setRecipient(u);
//			copyMessage.setSubject(message.getSubject());
//			copyMessage.setBody(message.getBody());
//		
//			message.setRecipient(u);
//			save(message);
//			
//			copyMessage.setFolder(inbox);
//			save(copyMessage);
//		}
//	}
	
	public void moveToTrash(Message message) {
		Assert.notNull(message);

		User user = userService.findByPrincipal();
		Folder folder = message.getFolder();
		Folder trash = folderService.getTrash(user.getUserAccount());

		message.setFolder(trash);

		save(message);
		folder.getMessages().remove(message);
		trash.getMessages().add(message);

	}

	public boolean checkPrincipalIsSender(Message message) {

		Assert.notNull(message);

		boolean result = false;
		UserAccount principalUserAccount;
		UserAccount userAccount;
		User sender;

		sender = message.getSender();
		principalUserAccount = LoginService.getPrincipal();
		userAccount = sender.getUserAccount();

		if (principalUserAccount.equals(userAccount)) {
			result = true;
		}

		return result;
	}

	public boolean checkPrincipalIsRecipient(Message message) {

		Assert.notNull(message);

		boolean result = false;
		UserAccount principalUserAccount;
		UserAccount userAccount;
		User recipient;

		recipient = message.getRecipient();
		principalUserAccount = LoginService.getPrincipal();
		userAccount = recipient.getUserAccount();

		if (principalUserAccount.equals(userAccount)) {
			result = true;
		}

		return result;
	}

}
