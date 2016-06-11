package rest.app.sceleton.user.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -1013877032615846624L;

	private Long id;

	@NotEmpty(message = "email.may.not.be.empty")
	private String email;

	// TODO better validation
	@NotEmpty(message = "password.may.not.be.empty")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
