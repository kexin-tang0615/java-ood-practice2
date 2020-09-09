package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CmdLineParserTest {

  private static final String INPUT_EMAIL = "--output-dir emails --email --csv-file insurance-company-members.csv --email-template email-template.txt";
  private static final String INPUT_LETTER = "--output-dir letters --letter --csv-file insurance-company-members.csv --letter-template letter-template.txt";
  private static final String INPUT_EMAIL_NO_TEMPLATE = "--output-dir emails --email --csv-file insurance-company-members.csv --email-template --email";
  private static final String INPUT_LETTER_NO_TEMPLATE = "--output-dir letters --letter --csv-file insurance-company-members.csv letter-template.txt --letter-template";
  private static final String INPUT_EMAIL_NO_OUTPUT = "emails --output-dir --email --csv-file insurance-company-members.csv --email-template email-template.txt";
  private static final String INPUT_LETTER_NO_CSV = "--output-dir letters --letter insurance-company-members.csv --csv-file --letter-template letter-template.txt";
  private static final String INPUT_EMAIL_NO_CSV = "--output-dir emails --email --csv-file insurance-company-members.csv --letter-template email-template.txt";
  private static final String INPUT_LETTER_WRONG_TEMPLATE = "--output-dir letters --letter --csv-file insurance-company-members.csv --email-template letter-template.txt";
  private static final String NON_SENSE_PARAM = "Dharma";
  private static final String INPUT_EMAIL_CSV_MISSING = "--output-dir emails --email --email-template email-template.txt";


  private CmdLineParser cmdLineParser1;
  private CmdLineParser cmdLineParser2;
  private String[] inputArguments1;
  private String[] inputArguments2;


  @Before
  public void setUp() throws Exception {
    cmdLineParser1 = new CmdLineParser();
    cmdLineParser2 = new CmdLineParser();
    inputArguments1 = INPUT_EMAIL.split(" ");
    inputArguments2 = INPUT_LETTER.split(" ");
    cmdLineParser1.parse(inputArguments1);
    cmdLineParser2.parse(inputArguments2);
  }

  @Test
  public void getEmailTemplate() {
    assertEquals("email-template.txt", cmdLineParser1.getEmailTemplate());
  }

  @Test
  public void isEmail() {
    assertTrue(cmdLineParser1.isEmailFlag());
  }

  @Test
  public void getLetterTemplate() {
    assertEquals("letter-template.txt", cmdLineParser2.getLetterTemplate());
  }

  @Test
  public void isLetter() {
    assertTrue(cmdLineParser2.isLetterFlag());
  }

  @Test
  public void getCsvFile() {
    assertEquals("insurance-company-members.csv", cmdLineParser1.getCsvFile());
  }

  @Test
  public void getOutputDir() {
    assertEquals("emails", cmdLineParser1.getOutputDir());
  }

  @Test
  public void isValidInput() {
    assertTrue(cmdLineParser1.isValidInput(inputArguments1, 1));
    assertFalse(cmdLineParser2.isValidInput(inputArguments2, 0));
    assertFalse(cmdLineParser1.isValidInput(inputArguments1, 7));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidNoSenseParams() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(NON_SENSE_PARAM.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidParameters2() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_EMAIL_NO_TEMPLATE.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidNoLetterTemplate() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_LETTER_NO_TEMPLATE.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidNoEmailOutput() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_EMAIL_NO_OUTPUT.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidLetterNoCsv() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_LETTER_NO_CSV.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidEmailNoCsv() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_EMAIL_NO_CSV.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidLetterWrongTemplate() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_LETTER_WRONG_TEMPLATE.split(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseInvalidMissCsv() {
    CmdLineParser parser = new CmdLineParser();
    parser.parse(INPUT_EMAIL_CSV_MISSING.split(" "));
  }

  @Test
  public void testToString() {
    assertEquals(
        "This is a commandline parser -- "
            + "CmdLineParser{emailTemplate='email-template.txt', isEmail=true, "
            + "letterTemplate='null', isLetter=false, csvFile='insurance-company-members.csv', "
            + "outputDir='emails'}",
        cmdLineParser1.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(1235171809, cmdLineParser1.hashCode());
  }

  @Test
  public void testEquals() {
    assertNotEquals(cmdLineParser1, null);
    assertNotEquals(cmdLineParser1, new Object());
    assertEquals(cmdLineParser1, cmdLineParser1);
    assertNotEquals(cmdLineParser1, cmdLineParser2);

    String inputEmailDiffCsv = "--output-dir emails --email --csv-file insurance-company-members1.csv --email-template email-template.txt";
    CmdLineParser parser1 = new CmdLineParser();
    parser1.parse(inputEmailDiffCsv.split(" "));
    assertNotEquals(cmdLineParser1, parser1);

  }
}