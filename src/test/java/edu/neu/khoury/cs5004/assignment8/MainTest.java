package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

  private static final String INPUT_EMAIL =
      "--output-dir test_main_email_out --email --csv-file files_for_test/csv_file.csv --email-template files_for_test/template2.txt";

  private static final String INPUT_LETTER =
      "--output-dir test_main_letter_out --letter --csv-file files_for_test/csv_file.csv --letter-template files_for_test/template2.txt";

  private static final String ONE_EXAMPLE =
      "Dear Josephine Darakjy,\n"
          + "Similarly, we also have a template for the company's letter:\n";

  private static final String ERR_MSG =
      "Error: Unrecognized parameter.\n"
          + "Usage:\n"
          + " --email only generate email messages\n"
          + " --email-template <file> accept a filename that holds the\n"
          + "email template.\n"
          + " Required if --email is used\n"
          + " --letter only generate letters\n"
          + " --letter-template <file> accept a filename that holds\n"
          + "the email template.\n"
          + " Required if --letter is used\n"
          + " --output-dir <path> accept the name of a folder, all\n"
          + "output is placed in this folder\n"
          + " --csv-file <path> accept the name of the csv file to\n"
          + "process\n"
          + "\n"
          + "Examples:\n"
          + " --email --email-template email-template.txt --output-dir emails\n"
          + " --csv-file customer.csv\n"
          + " --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv\n\n";

  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalErr = System.err;


  @Before
  public void setUpStreams() {
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setErr(originalErr);
  }

  @Test
  public void testMainEmail() throws Exception {
    Main.main(INPUT_EMAIL.split(" "));
    String generated = FileUtils.readFromFile("test_main_email_out/Email1.txt");
    assertEquals(ONE_EXAMPLE, generated);
  }

  @Test
  public void testMainLetter() throws Exception {
    Main.main(INPUT_LETTER.split(" "));
    String generated = FileUtils.readFromFile("test_main_letter_out/Letter1.txt");
    assertEquals(ONE_EXAMPLE, generated);
  }

  @Test
  public void testMainInvalidParams() {
    Main.main("abc def".split(" "));
    assertEquals(ERR_MSG, errContent.toString());
  }
}
