package rest.app.sceleton.base;

import org.apache.commons.lang.RandomStringUtils;

public class StringUtils {

	private final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static boolean isNullOrEmpty(String text) {
		return text == null || text.isEmpty();
	}

	public static String randomHexString(int length) {
		return RandomStringUtils.random(length, HEX_ARRAY);
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];

		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}

		return new String(hexChars);
	}

}
