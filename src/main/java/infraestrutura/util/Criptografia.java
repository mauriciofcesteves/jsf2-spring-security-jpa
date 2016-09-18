package infraestrutura.util;

import infraestrutura.model.MetodoCriptografia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public static final String SHA256 = "sha-256";
	public static final String MD5 = "md5";
	
	public static String md5(String senha, MetodoCriptografia metodo) {  
		return criptografar(senha, MetodoCriptografia.MD5);
	}  

	public static String sha256(String senha) {  
		return criptografar(senha, MetodoCriptografia.SHA256);
	}  

	public static String criptografar(String senha, MetodoCriptografia metodo) {  
		String sen = "";  
		MessageDigest md = null;  
		try {  
			md = MessageDigest.getInstance(metodo.getMetodo());  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}  
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
		sen = hash.toString(16);  
		return sen;  
	}  

	public static void main(String[] args) {
		System.out.println(criptografar("admin", MetodoCriptografia.SHA256));
		System.out.println(criptografar("sec", MetodoCriptografia.SHA256));
		System.out.println(criptografar("gop", MetodoCriptografia.SHA256));
	}
	
}
