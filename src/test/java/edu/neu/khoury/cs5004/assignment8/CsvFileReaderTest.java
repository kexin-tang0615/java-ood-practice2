package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CsvFileReaderTest {

  private CsvFileReader csvFileReader;
  private CsvFileReader csvFileReader2;
  private CsvFileReader csvFileReader3;

  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalErr = System.err;

  @Before
  public void setUp() {
    String csvFile = "files_for_test/csv_file.csv";
    String csvFile2 = "files_for_test/csv_file2.csv";
    csvFileReader = new CsvFileReader(csvFile);
    csvFileReader2 = new CsvFileReader(csvFile2);
    String csvFile3 = "file_for_test/non_exist.csv";
    csvFileReader3 = new CsvFileReader(csvFile3);

    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setErr(originalErr);
  }

  @Test
  public void testReadCsvFileHeader() {

    assertEquals("\"first_name\",\"last_name\",\"company_name\",\"address\"",
        csvFileReader.readCsvFileHeader());
  }

  @Test
  public void testReadCsvFileHeaderFail() {

    assertEquals("", csvFileReader3.readCsvFileHeader());
    assertTrue(errContent.toString().contains("Error reading a file"));
  }

  @Test
  public void testReadCsvFileContent() {
    List<String> listOfStrings = new ArrayList<>();
    String rowOne = "\"James\",\"Butt\",\"Benton, John B Jr\",\"6649 N Blue Gum St\"";
    String rowTwo = "\"Josephine\",\"Darakjy\",\"Chanay, Jeffrey A Esq\",\"4 B Blue Ridge Blvd\"";
    listOfStrings.add(rowOne);
    listOfStrings.add(rowTwo);
    assertEquals(listOfStrings, csvFileReader.readCsvFileContent());
  }

  @Test
  public void testReadCsvFileContentFail() {
    List<String> empty = new ArrayList<>();
    assertEquals(empty,
        csvFileReader3.readCsvFileContent());
    assertTrue(errContent.toString().contains("Error reading file"));
  }

  @Test
  public void testEquals() {
    assertTrue(csvFileReader.equals(csvFileReader));
  }

  @Test
  public void testEquals2() {
    assertFalse(csvFileReader.equals(csvFileReader2));
  }

  @Test
  public void testEquals3() {
    assertFalse(csvFileReader.equals(null));
  }

  @Test
  public void testEquals4() {
    assertFalse(csvFileReader.equals("a"));
  }

  @Test
  public void testHashCode() {
    assertFalse(csvFileReader.hashCode() == csvFileReader2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("This is a file reader with reading a csv file: files_for_test/csv_file.csv",
        csvFileReader.toString());
  }
}