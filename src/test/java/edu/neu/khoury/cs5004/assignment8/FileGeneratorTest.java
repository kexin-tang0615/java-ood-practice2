package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class FileGeneratorTest {

  private FileGenerator fileGenerator;
  private FileGenerator fileGenerator2;

  @Before
  public void setUp() throws Exception {
    String templateFile = "files_for_test/template.txt";
    String template = new TemplateFileReader(templateFile).readTemplateFile();
    String csvFile = "files_for_test/csv_file.csv";
    CsvFileReader csvFileReader = new CsvFileReader(csvFile);
    String header = csvFileReader.readCsvFileHeader();
    List<String> contents = csvFileReader.readCsvFileContent();
    CsvFileParser csvFileParser = new CsvFileParser();
    HashSet<String> hashSet = csvFileParser.constructHeaderSet(header);
    List<Map<String, String>> csvData = csvFileParser.parseCsvFile(contents, header);
    TemplateParser templateParser = new TemplateParser();
    fileGenerator = new FileGenerator();
    fileGenerator2 = new FileGenerator();
    fileGenerator.generateFile(templateParser, template, csvData, hashSet);

  }

  @Test
  public void equals() {
    assertTrue(fileGenerator.equals(fileGenerator));
  }

  @Test
  public void equals2() {
    assertFalse(fileGenerator.equals(fileGenerator2));
  }

  @Test
  public void equals3() {
    assertFalse(fileGenerator.equals(null));
  }

  @Test
  public void equals4() {
    assertFalse(fileGenerator.equals("a"));
  }

  @Test
  public void hashCode1() {
    assertFalse(fileGenerator.hashCode() == fileGenerator2.hashCode());
  }

  @Test
  public void toString1() {
    // The header in the test file only contains four types.
    // Those header types which are not in the test file are not replaced.
    assertEquals("This is a file generator!\n"
        + "List of new files are as follows:\n"
        + "[Dear James Butt,\n"
        + "\n"
        + "Similarly, we also have a template for the company's letter: \n"
        + "James Butt\n"
        + "6649 N Blue Gum St, [[city]],\n"
        + "[[county]], [[state]], [[zip]]\n"
        + ", Dear Josephine Darakjy,\n"
        + "\n"
        + "Similarly, we also have a template for the company's letter: \n"
        + "Josephine Darakjy\n"
        + "4 B Blue Ridge Blvd, [[city]],\n"
        + "[[county]], [[state]], [[zip]]\n" + "]", fileGenerator.toString());
  }
}