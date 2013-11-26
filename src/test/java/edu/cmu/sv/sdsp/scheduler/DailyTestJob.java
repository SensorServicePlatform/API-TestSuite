package edu.cmu.sv.sdsp.scheduler;

import java.util.Properties;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.cmu.sv.sdsp.api.APIUnitTest;

/**
 * This is Daily test job. Use username and password to send emails to to_username.
 * If find a failure, send emails to notify to_username.
 * 
 * @author Gonghan
 *
 */
public class DailyTestJob implements Job {
	final String username = "sensorplatformapitest@gmail.com";
	final String password = "siliconvalley";
	final String[] to_username = { "wanggonghan@gmail.com" };

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			JUnitCore apiTest = new JUnitCore();
			Result result = apiTest.run(APIUnitTest.class);
			if (result.getFailureCount() >= 1) {
				// email somebody
				String text = String.format(
						"When running API tests, got %d failure(s).",
						result.getFailureCount());
				for (String to : to_username) {
					sendEmails(text, to);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmails(String text,String to) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
