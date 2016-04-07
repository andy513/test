package com.andy.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.SerializationUtils;

import com.andy.biz.UserBiz;
import com.andy.biz.impl.UserBizImpl;
import com.andy.common.SpringBeans;
import com.andy.entity.User;

public class Test {
	
	private void methodA(String str,Integer...integers){
		
	}
	
	private void methodA(String str,String...strings){
		
	}
	
	public static void main(String[] args) {
		UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class);
		int result = userBiz.addUser(new User("1", "uname", "password"));
		System.out.println(result);
	}
	
	
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	public final static String MD6(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	private static String Md5(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuilder buf = new StringBuilder();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	  private static Date date = new Date();
	  private static int seq = 0;
	  private static final int ROTATION = 9999;
	  public static final synchronized String UUID(){
	    if (seq >= ROTATION) {
	    	date.setTime(System.currentTimeMillis());
	    	seq = 0;
	    }
	    String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$04d", date, seq++);
	    return str.substring(2);
	  }

	public static void test() {
		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();

		String passwd = null;
		String loginpasswd = null;
		passwd = "123qaz"; // 密码明文
		loginpasswd = Md5(passwd);
		String u = loginpasswd.toUpperCase();
		String l = loginpasswd.toUpperCase();
		map.put(u, "a");
		map.put(l, "a");
		System.out.println("MD5 16Bit : " + loginpasswd.toUpperCase());
		System.out.println(map);

		String s = new String("a");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + MD5(s));
		System.out.println("MD5后再加密：" + KL(MD5(s)));
		System.out.println("解密为MD5后的：" + JM(KL(MD5(s))));
	}
}
