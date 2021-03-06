package com.udacity.jwdnd.course1.cloudstorage;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {


    @FindBy(id = "logoutBtn")
    private WebElement logoutButton;



    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;



    @FindBy(id = "AddNewNoteBtn")
    private WebElement btnAddNewNote;



    @FindBy(id = "EditNoteBtn")
    private WebElement btnEditNote;

    @FindBy(id = "DeleteNoteBtn")
    private WebElement btnDeleteNote;



    @FindBy(id = "note-title")
    private WebElement txtNoteTitle;

    @FindBy(id = "note-description")
    private WebElement txtNoteDescription;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "SaveChangesBtn")
    private WebElement btnSaveChanges;

    @FindBy(id = "AddNewCredentialBtn")
    private WebElement btnAddNewCredential;

    @FindBy(id = "EditCredentialBtn")
    private WebElement btnEditCredential;

    @FindBy(id = "DeleteCredentialBtn")
    private WebElement btnDeleteCredential;

    @FindBy(id = "tableCredentialUrl")
    private WebElement tableCredentialUrl;

    @FindBy(id = "tableCredentialUsername")
    private WebElement tableCredentialUsername;

    @FindBy(id = "tableCredentialPassword")
    private WebElement tableCredentialPassword;

    @FindBy(id = "credential-url")
    private WebElement txtCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement txtCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement txtCredentialPassword;

    @FindBy(id = "CredentialSaveChangesBtn")
    private WebElement btnCredentialSaveChanges;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 500);
    }

    public void logout() {
        logoutButton.click();
    }


    //Navigation bar

    public void navToNotesTab() {

        js.executeScript("arguments[0].click();", navNotesTab);

    }

    public void navToCredentialsTab() {
        js.executeScript("arguments[0].click();", navCredentialsTab);

    }


    //Notes

    public void editNote() {
        js.executeScript("arguments[0].click();", btnEditNote);
    }

    public void deleteNote() {
        js.executeScript("arguments[0].click();", btnDeleteNote);
    }

    public void addNewNote() {
        js.executeScript("arguments[0].click();", btnAddNewNote);
    }

    public void setNoteTitle(String noteTitle) {
        js.executeScript("arguments[0].value='"+ noteTitle +"';", txtNoteTitle);
    }

    public void setNoteDescription(String noteDescription) {
        js.executeScript("arguments[0].value='"+ noteDescription +"';", txtNoteDescription);
    }


    public void saveNoteChanges() {
        js.executeScript("arguments[0].click();", btnSaveChanges);
    }

    public Note getFirstNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String description = wait.until(ExpectedConditions.elementToBeClickable(tableNoteDescription)).getText();
        return new Note(title, description);
    }

    public boolean isNoteTableEmpty(WebDriver driver) {
        return !isElementPresent(By.id("tableNoteTitle"), driver) && !isElementPresent(By.id("tableNoteDescription"), driver);
    }

    //End notes


    //Credentials

    public void editCredential() {
        js.executeScript("arguments[0].click();", btnEditCredential);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", btnDeleteCredential);
    }

    public void addNewCredential() {
        js.executeScript("arguments[0].click();", btnAddNewCredential);
    }

    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='"+ url +"';", txtCredentialUrl);
    }

    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='"+ username +"';", txtCredentialUsername);

    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", txtCredentialPassword);

    }

    public void saveCredentialChanges() {
        js.executeScript("arguments[0].click();", btnCredentialSaveChanges);
    }

    public Credential getFirstCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tableCredentialUrl)).getText();
        String username = wait.until(ExpectedConditions.elementToBeClickable(tableCredentialUsername)).getText();
        String password = wait.until(ExpectedConditions.elementToBeClickable(tableCredentialPassword)).getText();
        return new Credential(url, username, password);
    }

    public boolean isCredentialsTableEmpty(WebDriver driver) {
        return !isElementPresent(By.id("tableCredentialUrl"), driver) &&
                !isElementPresent(By.id("tableCredentialUsername"), driver) &&
                !isElementPresent(By.id("tableCredentialPassword"), driver);
    }


    //End credentials


    public boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
