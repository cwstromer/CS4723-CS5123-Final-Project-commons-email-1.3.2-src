package org.apache.commons.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import junit.framework.TestCase;

import javax.mail.internet.MimeMultipart;
import java.util.Date;

public class TemplateTest extends TestCase{
	Email testEmail;
	public void setUp() {
		testEmail = new SimpleEmail();
	}

	public void testAddBcc() throws EmailException {
		System.out.println("This is a message from the template test.");
		testEmail.addBcc("a@b.com");
		assertEquals("a@b.com", testEmail.getBccAddresses().get(0).toString());
	}

	// test addBcc with an array of String inputs
	public void testAddBccString() throws EmailException {
		String[] emails = {"a@b.com", "c@d.com", "e@f.com", "h@i.com"};
		testEmail.addBcc(emails);
		assertEquals("a@b.com", testEmail.getBccAddresses().get(0).toString());
		assertEquals("c@d.com", testEmail.getBccAddresses().get(1).toString());
		assertEquals("e@f.com", testEmail.getBccAddresses().get(2).toString());
		assertEquals("h@i.com", testEmail.getBccAddresses().get(3).toString());
	}

	// test addBcc with an empty input
	public void testAddBccEmpty() throws EmailException {
		String[] emails = {};
		try {
			testEmail.addBcc(emails);
			fail("Failed to throw exception!");
		}catch(EmailException e) {

		}
	}

	// test addBcc with a null input
	public void testAddBccNull() throws EmailException {
		String[] emails = null;
		try {
			testEmail.addBcc(emails);
			fail("Failed to throw exception!");
		}catch(EmailException e) {

		}
	}

	// test getMailSession with an empty host name
	public void testGetSetMailSessionWithEmptyHostName() throws EmailException {
		try {
			testEmail.getMailSession();
			fail("Email exception should be thrown with empty host.");
		}catch(EmailException e) {
			;
		}
	}

	// test getMailSession with host name
	public void testGetSetMailSessionWithHostName() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.getMailSession();
	}

	// test getMailSession with a authenticator
	public void testGetSetMailSessionWithAuthenticator() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setAuthenticator(new DefaultAuthenticator("abc", "123"));
		testEmail.getMailSession();
	}

	// test getMailSession with SSL on connect
	public void testGetSetMailSessionWithSSLOnConnect() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setSSLOnConnect(true);
		testEmail.getMailSession();
	}

	/*
	// test send with a basic subject example
	public void testSend() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.send();
		assertEquals("a subject", testEmail.getSent());
	}
	 */

	// test updateContentType with a charset
	public void testUpdateContentTypeWithCharset() {
		testEmail.updateContentType("text/plain; charset=utf-8");
	}

	// test updateContentType without a charset
	public void testUpdateContentTypeWithoutCharset() {
		testEmail.setCharset("utf-8");
		testEmail.updateContentType("text/plain");
	}

	// test buildMimeMessage with no from
	public void testBuildMimeMessageNoFrom() throws EmailException {
		testEmail.setHostName("b.com");
		try {
			testEmail.buildMimeMessage();
			fail("Should throw EmailException");
		}
		catch (EmailException e){
			assertEquals("From address required", e.getMessage());
		}
	}

	// test buildMimeMessage with no to
	public void testBuildMimeMessageNoTo() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		try {
			testEmail.buildMimeMessage();
			fail("Should throw EmailException");
		}
		catch (EmailException e){
			assertEquals("At least one receiver address required", e.getMessage());
		}
	}

	// test an existing buildMimeMessage
	public void testBuildMimeMessageExisting() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");

		testEmail.buildMimeMessage();
		try {
			testEmail.buildMimeMessage();
			fail("Should throw illegal state Exception");
		}
		catch (IllegalStateException e){
			assertEquals("The MimeMessage is already built.", e.getMessage());
		}
	}

	// test a full buildMimeMessage
	public void testBuildMimeMessageFull() throws EmailException {
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("a subject");
		testEmail.setCharset("UTF-8");
		testEmail.setContent("abc", EmailConstants.TEXT_PLAIN);
		testEmail.addCc("d@b.com");
		testEmail.addBcc("e@b.com");
		testEmail.addReplyTo("f@b.com");
		testEmail.addHeader("header", "abc");
		testEmail.setContent(new MimeMultipart());

		testEmail.buildMimeMessage();
		try {
			testEmail.buildMimeMessage();
			fail("Should throw illegal state Exception");
		}
		catch (IllegalStateException e){
			assertEquals("The MimeMessage is already built.", e.getMessage());
		}
	}

	// test addHeader with no name
	public void testAddHeaderNoName() throws EmailException {
		String name = "";
		String value = "123";
		try {
			testEmail.addHeader(name, value);
			fail("Failed to throw exception!");
		}catch(IllegalArgumentException e) {

		}
	}
/*
	// test getSentDate with a null date value
	public void testGetSentDateNull() {
		testEmail.setSentDate(null);
		testEmail.getSent();
	}

*/

	// test getSentDate with a date value
	public void testGetSentDate() {
		Date date = new Date();
		testEmail.setSentDate(date);
		assertEquals(date, testEmail.getSentDate());
	}

	// test getHostName with a valid host name
	public void testGetHostName() {
		testEmail.setHostName("b.com");
		assertEquals("b.com", testEmail.getHostName());
	}

	// test getHostName with a null host name
	public void testGetHostNameNull() {
		testEmail.setHostName(null);
		assertEquals(null, testEmail.getHostName());
	}

	// test getSocketConnectionTimeout with a random int
	public void testGetSocketConnectionTimeout() {
		testEmail.setHostName("b.com");
		testEmail.setSocketConnectionTimeout(1);
		assertEquals(1, testEmail.getSocketConnectionTimeout());
	}
}
