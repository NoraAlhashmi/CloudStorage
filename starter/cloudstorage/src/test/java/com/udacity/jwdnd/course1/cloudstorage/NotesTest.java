package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest extends CloudStorageApplicationTests {

    //Write a test that creates a note, and verifies it is displayed.
    @Test
    public void testAddNote() {
        String noteTitle = "My Note";
        String noteDescription = "Note Description";
        HomePage homePage = goToHome();

        homePage.navToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTitle);
        homePage.setNoteDescription(noteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();

        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        Note note = homePage.getFirstNote();

        //Next two lines verifies that the last added note details matches the test input
        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());

        homePage.deleteNote();
        resultPage.clickOk();
    }



    //Write a test that edits an existing note and verifies that the changes are displayed.
    @Test
    public void testEditNote() {
        HomePage homePage = goToHome();
        homePage.navToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle("My Note");
        homePage.setNoteDescription("Note Description");
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        homePage.editNote();
        homePage.setNoteTitle("Edited Note");
        homePage.setNoteDescription("Edited note description");
        homePage.saveNoteChanges();
        resultPage.clickOk();
        homePage.navToNotesTab();
        Note note = homePage.getFirstNote();

        //Next two lines verifies that the edited note details matches the test input
        Assertions.assertEquals("Edited Note", note.getNoteTitle());
        Assertions.assertEquals("Edited note description", note.getNoteDescription());

        homePage.deleteNote();
        resultPage.clickOk();
    }

    //Write a test that deletes a note and verifies that the note is no longer displayed.
    @Test
    public void testDelete() {
        HomePage homePage = goToHome();
        homePage.navToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle("My Note");
        homePage.setNoteDescription("Note Description");
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        homePage.deleteNote();
        resultPage.clickOk();
        //Next line verifies that there is notes
        Assertions.assertTrue(homePage.isNoteTableEmpty(driver));
    }



}