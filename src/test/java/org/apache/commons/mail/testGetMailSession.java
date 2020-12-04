package org.apache.commons.mail;

import junit.framework.TestCase;

public class testGetMailSession extends TestCase {
    Email testEmail;
    public void setUp() {
        testEmail = new SimpleEmail();
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
}
