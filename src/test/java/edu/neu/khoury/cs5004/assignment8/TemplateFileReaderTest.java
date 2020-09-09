package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TemplateFileReaderTest {

  private TemplateFileReader templateFileReader;
  private TemplateFileReader templateFileReader2;
  private TemplateFileReader templateFileReader3;
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalErr = System.err;

  @Before
  public void setUp() throws Exception {
    String templateName = "files_for_test/template.txt";
    templateFileReader = new TemplateFileReader(templateName);
    String templateName2 = "files_for_test/template2.txt";
    templateFileReader2 = new TemplateFileReader(templateName2);
    String invalidName = "files_for_test/temple.txt";
    templateFileReader3 = new TemplateFileReader(invalidName);

    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setErr(originalErr);
  }

  @Test
  public void readTemplateFile() throws IOException {
    assertEquals("Dear [[first_name]] [[last_name]],\n"
        + "\n"
        + "Similarly, we also have a template for the company's letter: \n"
        + "[[first_name]] [[last_name]]\n"
        + "[[address]], [[city]],\n"
        + "[[county]], [[state]], [[zip]]\n", templateFileReader.readTemplateFile());
  }

  @Test
  public void testReadCsvFileHeaderFail() {
    assertEquals("", templateFileReader3.readTemplateFile());
    assertTrue(errContent.toString().contains("Error reading a file"));
  }

  @Test
  public void testEquals() {
    assertTrue(templateFileReader.equals(templateFileReader));
  }

  @Test
  public void testEquals2() {
    assertFalse(templateFileReader.equals(null));
  }

  @Test
  public void testEquals3() {
    assertFalse(templateFileReader.equals("a"));
  }

  @Test
  public void testEquals4() {
    assertFalse(templateFileReader.equals(templateFileReader2));
  }

  @Test
  public void testHashCode() {
    assertFalse(templateFileReader.hashCode() == templateFileReader2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(
        "This is a file reader with reading a template: files_for_test/template.txt",
        templateFileReader.toString());
  }
}
