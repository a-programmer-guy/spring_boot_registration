package email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
/*
 * This class implements the EmailSender class.
 * Import the sljf Logger and LoggerFactory for making logs on failed emails.
*/

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	// Async as to not block the client - TODO make a que for this
	
	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Email Confirmation");
			helper.setFrom("ctrl_k@ctrl-k.net");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// Log the failed email exception instead of sending it to the user
			LOGGER.error("Failed to send email", e);
			// This is the message the user will see
			throw new IllegalStateException("Failed to send email");
		}
	}

}
