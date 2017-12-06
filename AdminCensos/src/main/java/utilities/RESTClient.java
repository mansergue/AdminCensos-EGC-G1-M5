package utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.springframework.web.client.RestTemplate;
import domain.User;
import security.UserAccount;
public class RESTClient {

	/***
	 * MÃ©todo que lee un Json de autenticaciÃ³n y devuelve un map con los
	 * username de todos los usuarios registrados en el sistema como clave y sus
	 * emails como valor para que el admin del censo pueda aÃ±adir usuarios y se
	 * les notifique mediante email.
	 */

	public static Map<String, String> getMapUSernameAndEmailByJsonAutentication() {

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("https://authb.agoraus1.egc.duckdns.org/api/index.php?method=getUsers", String.class);

		String[] lista = result.split("},");

		Map<String, String> mapUsernamesEmails = new HashMap<String, String>();
		String username = null;
		String email;
		for (@SuppressWarnings("unused")String usuario : lista) {

			String[] lista2 = result.split(",");
			for (String campo : lista2) {
				String[] auxList = campo.split(":");
				if (campo.contains("username")) {
					username = auxList[1];
					username = username.replaceAll("\"", "");
					mapUsernamesEmails.put(username, null);

				}
				if (campo.contains("email")) {
					email = auxList[1];
					email = email.replaceAll("\"", "");
					mapUsernamesEmails.put(username, email);
				}

			}

		}

		return mapUsernamesEmails;
	}

	/***
	 * MÃ©todo que lee nos devuelve el Json con la informaciÃ³n de un usuario en
	 * concreto pasÃ¡ndole un username, el Json obtenido de autenticaciÃ³n se
	 * leerÃ¡ para formar un tipo User que serÃ¡ lo que se devuelva.
	 * @throws UnsupportedEncodingException 
	 */

	public static User getCertainUserByJsonAuthentication(String username){
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("https://authb.agoraus1.egc.duckdns.org/api/index.php?method=getUser&user=" + username,String.class);
		String[] lista = result.split(",");
		User user = new User();
		UserAccount userAccount=new UserAccount();
		for (String field : lista) {
			System.out.println("FIELD:------------"+field);
			if (field.contains("username")) {
				String[] auxList = field.split(":");
				String usernameUser = auxList[1];
				usernameUser = usernameUser.replaceAll("\"", "");
				userAccount.setUsername(usernameUser);
			}
			if (field.contains("name")&&!field.contains("surname")&&!field.contains("username")) {
				String[] auxList = field.split(":");
				String nameUser = auxList[1];
				nameUser = nameUser.replaceAll("\"", "");
				user.setName(nameUser);
				
			}
			if (field.contains("surname")) {
				String[] auxList = field.split(":");
				String surnameUser = auxList[1];
				surnameUser = surnameUser.replaceAll("\"", "");
				user.setSurname(surnameUser);
			}
			if (field.contains("email")) {
				String[] auxList = field.split(":");
				String email = auxList[1];
				email = email.replaceAll("\"", "");
				user.setEmail(email);
			}
			if (field.contains("genre")) {
				String[] auxList = field.split(":");
				String genre = auxList[1];
				genre = genre.replaceAll("\"", "");
				user.setGenre(genre);
			}
			if (field.contains("autonomous_community")) {
				String[] auxList = field.split(":");
				String autonomousCommunity = auxList[1];
				autonomousCommunity = autonomousCommunity.replaceAll("\"", "");
				user.setAutonomous_community(autonomousCommunity);
			}
			if (field.contains("age")) {
				String[] auxList = field.split(":");
				String age = auxList[1];
				String[] age_list = age.split("}");
				String finalAge = age_list[0];
				String[] s=finalAge.split("\"");
				int ageConverted = Integer.parseInt(s[1]);
				user.setAge(ageConverted);
			}
			if (field.contains("role")) {
				String[] auxList = field.split(":");
				String roleUser = auxList[1];
				roleUser = roleUser.replaceAll("\"", "");
				user.setRole(roleUser);
			}
		}
		user.setUserAccount(userAccount);
		
		return user;

	}
	
		public static void main(String[] args) throws IOException, ParseException{
		Map<String, String> usernamesAndEmails = getMapUSernameAndEmailByJsonAutentication();
		System.out.println(usernamesAndEmails);
		Set<String> aux= usernamesAndEmails.keySet();
		System.out.println(aux);
		for(String s:aux){
			System.out.println(s);
			User user =getCertainUserByJsonAuthentication(s);
			System.out.println(user.getUserAccount().getUsername());
			System.out.println(user.getName());
			System.out.println(user.getSurname());
			System.out.println(user.getEmail());
			System.out.println(user.getGenre());
			System.out.println(user.getAutonomous_community());
			System.out.println(user.getAge());
			System.out.println(user.getRole()+"\n"+"////////////////////////////////"+"\n");
		}
	}
}