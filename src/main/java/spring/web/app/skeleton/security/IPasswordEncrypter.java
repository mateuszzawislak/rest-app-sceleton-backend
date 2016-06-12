package spring.web.app.skeleton.security;

public interface IPasswordEncrypter {

	public String hash(String plain);

	public boolean check(String plain, String hashed);

}
