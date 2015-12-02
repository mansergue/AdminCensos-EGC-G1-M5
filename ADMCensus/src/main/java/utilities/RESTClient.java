package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.client.RestTemplate;

public class RESTClient {

	/***
	 * 
	 * Metodo que lee un Json de autenticación y devuelve una lista con los
	 * username de todos los usuarios registrados en el sistema para que el
	 * admin del censo pueda añadir usuarios.
	 * 
	 */
	public static Collection<String> getListUsernamesByJsonAutentication() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://auth-egc.azurewebsites.net/api/getUsers", String.class);
		String[] lista = result.split(",");

		Collection<String> usernames = new ArrayList<String>();
		for (String field : lista) {
			if (field.contains("username")) {
				String[] aux_list = field.split(":");
				String username = aux_list[1];
				username = username.replaceAll("\"", "");
				usernames.add(username);
			}
		}

		return usernames;
	}

	public static void main(String[] args) throws IOException {
		Collection<String> usernames = getListUsernamesByJsonAutentication();
		for (String st : usernames) {
			System.out.println(st);
		}
	}
}
