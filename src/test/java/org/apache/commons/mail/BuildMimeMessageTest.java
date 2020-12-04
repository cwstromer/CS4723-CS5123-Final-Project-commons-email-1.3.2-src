package org.apache.commons.mail;
import junit.framework.TestCase;

import javax.mail.internet.MimeMultipart;

public class BuildMimeMessageTest extends TestCase {
    Email testEmail;
    public void setUp() {
        testEmail = new SimpleEmail();
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
}
