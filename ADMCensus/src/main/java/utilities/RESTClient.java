package utilities;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class RESTClient {

	/***
	 * 
	 * Método que lee un Json de autenticación y devuelve un map con los
	 * username de todos los usuarios registrados en el sistema como clave y sus
	 * emails como valor para que el admin del censo pueda añadir usuarios y se
	 * les notifique mediante email.
	 * 
	 */

	public static Map<String, String> getMapUSernameAndEmailByJsonAutentication() {

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://auth-egc.azurewebsites.net/api/getUsers", String.class);
		String[] lista = result.split("},");

		Map<String, String> mapUsernamesEmails = new HashMap<String, String>();
		String username = null;
		String email;
		for (@SuppressWarnings("unused")
		String usuario : lista) {

			String[] lista2 = result.split(",");
			for (String campo : lista2) {
				String[] aux_list = campo.split(":");
				if (campo.contains("username")) {
					username = aux_list[1];
					username = username.replaceAll("\"", "");
					mapUsernamesEmails.put(username, null);

				}
				if (campo.contains("email")) {
					email = aux_list[1];
					email = email.replaceAll("\"", "");
					mapUsernamesEmails.put(username, email);
				}

			}

		}

		return mapUsernamesEmails;
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> usernamesAndEmails = getMapUSernameAndEmailByJsonAutentication();
		System.out.println(usernamesAndEmails);
	}

}
