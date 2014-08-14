package pl.kozervar.exap.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


import pl.kozervar.exap.model.person.Person;


public class BasicTest {

	public void basicTest() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		Person p = new Person();
		p.setName("test");
		p.setSurname("surname");
		p.setEmail("email");
		p.setPhoneNumber("1235123");
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.update(p.toString().getBytes("UTF-8"));
		byte[] hash = digest.digest();
        BigInteger bigInt = new BigInteger(1, hash);
        String s1 = bigInt.toString(16);
        
        hash = digest.digest();
        bigInt = new BigInteger(1, hash);
        String s2 = bigInt.toString(16);
        
		System.out.println(s1);
		System.out.println(s2);
        
		UUID fromString1 = UUID.nameUUIDFromBytes(p.toString().getBytes("UTF-8"));
		UUID fromString2 = UUID.nameUUIDFromBytes(p.toString().getBytes("UTF-8"));
		System.out.println(fromString1);
		System.out.println(fromString2);
	}
}
