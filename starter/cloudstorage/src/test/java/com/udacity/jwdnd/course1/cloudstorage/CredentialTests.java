package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests extends CloudStorageApplicationTests {

    public static final String GOOGLE_URL = "www.google.com";
    public static final String GOOGLE_USERNAME = "noragoogle";
    public static final String GOOGLE_PASSWORD = "123";
    public static final String TWITTER_URL = "www.twitter.com";
    public static final String TWITTER_USERNAME = "noratwitter";
    public static final String TWITTER_PASSWORD = "12345";


    //Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.

    @Test
    public void testAddCredential() {
        HomePage homePage = goToHome();
        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        homePage.setCredentialUrl("www.google.com");
        homePage.setCredentialUsername("noragoogle");
        homePage.setCredentialPassword("123");
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();

        Credential credential = homePage.getFirstCredential();
        //Next 2 lines verify that the shown credential details matches the user input
        Assertions.assertEquals("www.google.com", credential.getUrl());
        Assertions.assertEquals("noragoogle", credential.getUsername());
        //Next line verifies that the password is encrypted
        Assertions.assertNotEquals("123", credential.getPassword());

        homePage.deleteCredential();
        resultPage.clickOk();

        homePage.addNewCredential();
        homePage.setCredentialUrl("www.twitter.com");
        homePage.setCredentialUsername("noratwitter");
        homePage.setCredentialPassword("12345");
        homePage.saveCredentialChanges();
        resultPage.clickOk();
        homePage.navToCredentialsTab();

        Credential credential2 = homePage.getFirstCredential();
        //Next 2 lines verify that the shown credential details matches the user input
        Assertions.assertEquals("www.twitter.com", credential2.getUrl());
        Assertions.assertEquals("noratwitter", credential2.getUsername());
        //Next line verifies that the password is encrypted
        Assertions.assertNotEquals("12345", credential2.getPassword());

        homePage.deleteCredential();
        resultPage.clickOk();

    }


    //Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
    @Test
    public void testEditCredential() {
        HomePage homePage = goToHome();

        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        homePage.setCredentialUrl("www.google.com");
        homePage.setCredentialUsername("noragoogle");
        homePage.setCredentialPassword("123");
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        Credential credential = homePage.getFirstCredential();

        //Next line verifies that the password is encrypted
        Assertions.assertNotEquals("123", credential.getPassword());

        homePage.editCredential();

        homePage.setCredentialUrl("www.twitter.com");
        homePage.setCredentialUsername("noratwitter");
        homePage.setCredentialPassword("12345");
        homePage.saveCredentialChanges();
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        Credential editedCredential = homePage.getFirstCredential();

        //Next 2 lines verify that the edited credential details matches the user input
        Assertions.assertEquals("www.twitter.com", editedCredential.getUrl());
        Assertions.assertEquals("noratwitter", editedCredential.getUsername());

        homePage.deleteCredential();
        resultPage.clickOk();
    }

    //Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
    @Test
    public void testDeleteCredential() {
        HomePage homePage = goToHome();
        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        homePage.setCredentialUrl("www.google.com");
        homePage.setCredentialUsername("noragoogle");
        homePage.setCredentialPassword("123");
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        homePage.setCredentialUrl("www.twitter.com");
        homePage.setCredentialUsername("noratwitter");
        homePage.setCredentialPassword("12345");
        homePage.saveCredentialChanges();
        resultPage.clickOk();
        homePage.navToCredentialsTab();

        //Next line verifies that the table is not empty
        Assertions.assertFalse(homePage.isCredentialsTableEmpty(driver));
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        //Next line verifies that the table is  empty
        Assertions.assertTrue(homePage.isCredentialsTableEmpty(driver));
    }
}
