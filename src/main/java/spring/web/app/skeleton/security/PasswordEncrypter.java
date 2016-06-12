package spring.web.app.skeleton.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import spring.web.app.skeleton.base.StringUtils;

@Component
public class PasswordEncrypter implements IPasswordEncrypter {

	@Override
	public String hash(String plain) {
		return hash(plain, salt());
	}

	@Override
	public boolean check(String plain, String hashed) {
		if (StringUtils.isNullOrEmpty(hashed))
			return false;

		String[] hashedParts = hashed.split("\\$");

		if (hashedParts.length != 2)
			return false;

		return hashed.equals(hash(plain, hashedParts[0]));
	}

	private String hash(String plain, String salt) {
		String encodedPassword = String.format("%s%s", salt, plain);

		for (int i = 0; i < 4; ++i)
			encodedPassword = digest(encodedPassword);

		return String.format("%s$%s", salt, encodedPassword);
	}

	private static String digest(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(text != null ? text.getBytes(StandardCharsets.UTF_8) : "".getBytes(StandardCharsets.UTF_8));

			return StringUtils.bytesToHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private String salt() {
		return StringUtils.randomHexString(16);
	}
}
