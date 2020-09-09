package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class OutputWriterTest {

  private static final String TEST_OUT = "test_output_writer_out";
  private static final String TEST_TEMPLATE = "files_for_test/template2.txt";
  private static final String TEST_CSV = "files_for_test/csv_file.csv";
  private static final String ZERO_EXAMPLE = "Dear James Butt,\n"
      + "Similarly, we also have a template for the company's letter:\n";
  private static final String TEST_EXISTS_FILE = "test_exists_file";

  private OutputWriter writer;

  @Before
  public void setUp() {
    writer = new OutputWriter(TEST_OUT);
    File file = new File(TEST_OUT);
    assertTrue(file.exists());
  }

  @Test
  public void testCreateDir() throws IOException {
    File file = new File(TEST_OUT);
    file.mkdirs();
    File subFile = new File(file, "abc");
    subFile.createNewFile();

    writer = new OutputWriter(TEST_OUT);

    assertTrue(file.exists());
    file.delete();
  }

  @Test
  public void testDirNameConflict() throws IOException {
    File file = new File(TEST_EXISTS_FILE);
    if (!file.exists()) {
      file.createNewFile();
    }
    writer = new OutputWriter(TEST_EXISTS_FILE);
    assertTrue(file.isDirectory());
    file.delete();
  }

  @Test
  public void testWriteMails() throws Exception {
    writer.writeMails(TEST_TEMPLATE, TEST_CSV, true);
    String generated = FileUtils.readFromFile(TEST_OUT + "/Email0.txt");
    assertEquals(ZERO_EXAMPLE, generated);
  }

  @Test
  public void testToString() {
    assertEquals("This is a output writer.\n"
        + "The output dir is test_output_writer_out'}", writer.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(1377386802, writer.hashCode());
  }

  @Test
  public void testEquals() {
    assertNotEquals(writer, null);
    assertNotEquals(writer, new Object());
    assertEquals(writer, writer);

    OutputWriter writer1 = new OutputWriter(TEST_OUT);
    assertEquals(writer, writer1);
  }
}
