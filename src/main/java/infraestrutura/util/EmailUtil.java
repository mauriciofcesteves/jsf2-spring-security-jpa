//package infraestrutura.util;
//
//import infraestrutura.exception.IMessage;
//import infraestrutura.exception.InfoMessage;
//import infraestrutura.exception.WarningMessage;
//import infraestrutura.model.ParametroSistema;
//import infraestrutura.model.TemplateEmail;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.faces.application.FacesMessage;
//import javax.mail.Address;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMessage.RecipientType;
//
//public class EmailUtil {
//
//	public static final String SMTP_GMAIL = "smtp.gmail.com";
//	public static final String SMTP_HOTMAIL = "smtp.live.com";
//	public static final String PORTA_GMAIL = "465";
//	public static final String PORTA_HOTMAIL = "25";
//	public static final String SSL = "SSL";
//	public static final String TLS = "TLS";
//
//	private static void enviarEmail(String smtp, String porta, String protocolo, String email, String senha, String assunto, String mensagem, String ... para) throws AddressException, MessagingException{
//		Properties p = getProperties(smtp, porta, protocolo);
//
//		Autenticador autenticador = null;
//
//		if(senha!=null && !senha.isEmpty()){
//			autenticador = new Autenticador(email, senha);
//			p.put("mail.smtp.auth", "true");
//		}
//
//		Session session = Session.getInstance(p,autenticador);
//
//		MimeMessage message = new MimeMessage(session);
//
//		message.setFrom(new InternetAddress(email));
//		message.setSentDate(new Date());
//		message.setSubject(assunto);
//		message.setContent(mensagem,"text/html");
//
//		for(String e_mail : para){
//			message.addRecipient(RecipientType.TO, new InternetAddress(e_mail));
//		}
//
//		Transport.send(message);
//		System.out.println("Email enviado com sucesso!");
//
//	}
//
//	public static class Autenticador extends javax.mail.Authenticator{
//
//		String usuario;
//		String senha;
//
//		public Autenticador(String usuario, String senha){
//			this.usuario = usuario;
//			this.senha = senha;
//		}
//
//		@Override
//		protected PasswordAuthentication getPasswordAuthentication() {
//			PasswordAuthentication password = new PasswordAuthentication(usuario,senha);
//			return password;
//		}
//	}
//
//	private static Properties getProperties(String smtp, String porta, String protocolo){
//		Properties properties =  new Properties();
//		properties.put("mail.smtp.host", smtp);
//		properties.put("mail.smtp.port", porta);
//
//		if(TLS.equalsIgnoreCase(protocolo)){
//			properties.put("mail.smtp.starttls.enable", "true");
//		}else if(SSL.equalsIgnoreCase(protocolo)){
//			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		}
//
//		return properties;
//	}
//
//	public static void enviarEmail(ParametroSistema parametro, String assunto, String mensagem, String ... emails){
//		try {
//			EmailUtil.enviarEmail(parametro.getSmtp(), parametro.getPortaSmtp(),parametro.getProtocolo(), parametro.getEmail(),parametro.getSenha(), assunto, mensagem, emails);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void enviarEmailTratandoErro(ParametroSistema parametro, String assunto, String mensagem, String ... emails){
//		try {
//			EmailUtil.enviarEmail(parametro.getSmtp(), parametro.getPortaSmtp(),parametro.getProtocolo(), parametro.getEmail(),parametro.getSenha(), assunto, mensagem, emails);
//			ManagedBeanUtil.adicionarMensagem(InfoMessage.EMAIL_SUCESSO, FacesMessage.SEVERITY_WARN, getStringEmails(emails));
//		} catch (Exception e) {
//			e.printStackTrace();
//			ManagedBeanUtil.adicionarMensagem(WarningMessage.EMAIL_NAO_ENVIADO, FacesMessage.SEVERITY_WARN, getStringEmails(emails));
//		}
//	}
//	
//	public static String getStringEmails(String ... emails){
//		StringBuffer stringBuffer = new StringBuffer();
//		for(int i=0; i<emails.length; i++){
//			if(i>0){
//				stringBuffer.append(",");
//			}
//			stringBuffer.append(emails[i]);
//		}
//		return stringBuffer.toString();
//	}
//	
//	public static void enviarEmail(ParametroSistema parametro, TemplateEmail template, Map<String, IMessage> mapa, String ... emails){
//		
//		for (String para : emails) {
//			try {
////				EmailUtil.enviarEmail(parametro.getSmtp(), parametro.getPortaSmtp(),parametro.getProtocolo(), parametro.getEmail(),parametro.getSenha(), template.getAssunto(), template.getCorpo(), para);
////				mapa.put(para, InfoMessage.EMAIL_INFORMADO_SUCESSO);
//			} catch (Exception e) {
//				e.printStackTrace();
////				mapa.put(para, ErrorMessage.ERRO_EMAIL_INFORMADO_NAO_ENVIADO);
//			}
//		}
//	}
//	
//	 public static void main(String[] args) throws AddressException, MessagingException {
//		  //objeto para definicao das propriedades de configuracao do provider
//		  Properties props = new Properties();
//		  //o valor "smtp.host.com.br" deve ser alterado para o seu servidor SMTP
//		  props.put("mail.host","server2.shservidores19.com.br"); 
//		  //obtendo um Session com a configura��o 
//		  //do servidor SMTP definida em props
//		  Session session = Session.getDefaultInstance(props);
//		  //criando a mensagem
//		  MimeMessage message = new MimeMessage(session);
//		  //substituir pelos e-mails desejados
//		  Address from = new InternetAddress("noreply@pos.shservidores19.com.br");
//		  Address to = new InternetAddress("na1.jefferson@gmail.com");
//		  //configurando o remetente e o destinatario
//		  message.setFrom(from);
//		  message.addRecipient(RecipientType.TO, to);
//		  //configurando a data de envio,  o assunto e o texto da mensagem
//		  message.setSentDate(new Date());
//		  message.setSubject("Assunto da mensagem");
//		  message.setText("Texto da mensagem!");
//		  //enviando
//		  Transport.send(message);
//		 }
//
//}