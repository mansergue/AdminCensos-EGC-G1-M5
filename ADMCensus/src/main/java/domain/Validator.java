package domain;

import javax.validation.constraints.NotNull;

public class Validator {

	boolean valid;

	@NotNull
	public boolean isValid() {
		return valid;
	}



}