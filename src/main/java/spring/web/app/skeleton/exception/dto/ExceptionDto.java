package spring.web.app.skeleton.exception.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExceptionDto implements Serializable {

	private static final long serialVersionUID = 3908701859928775093L;

	private int code;

	private List<String> messages;

	public ExceptionDto() {
		messages = new ArrayList<>();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

}
