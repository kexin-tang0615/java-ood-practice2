package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import edu.neu.khoury.cs5004.assignment8.TemplateParser.HeaderPair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TemplateParserTest {

  private HeaderPair headerPair;
  private TemplateParser templateParser;

  @Before
  public void setUp() throws Exception {
    Integer placeholderFrontPointer = 0;
    String headerType = "[[first_name]]";
    headerPair = new HeaderPair(placeholderFrontPointer, headerType);
    String templateFile = "files_for_test/template.txt";
    String template = new TemplateFileReader(templateFile).readTemplateFile();
    String csvFile = "files_for_test/csv_file.csv";
    CsvFileReader csvFileReader = new CsvFileReader(csvFile);
    String header = csvFileReader.readCsvFileHeader();
    CsvFileParser csvFileParser = new CsvFileParser();
    HashSet<String> hashSet = csvFileParser.constructHeaderSet(header);
    templateParser = new TemplateParser();
    templateParser.findPlaceholderFrontPointer(hashSet, template);

  }

  @Test
  public void getPlaceholderFrontPointer() {
    assertEquals((Integer) 0, headerPair.getPlaceholderFrontPointer());
  }

  @Test
  public void getHeaderType() {
    assertEquals("[[first_name]]", headerPair.getHeaderType());
  }

  @Test
  public void toString1() {
    assertEquals("HeaderPair{placeholderFrontPointer=0, headerType='[[first_name]]'}",
        headerPair.toString());
  }

  @Test
  public void findPlaceholderFrontPointer() {
    Integer placeholderFrontPointer = 5;
    String headerType = "[[first_name]]";
    HeaderPair headerPair = new HeaderPair(placeholderFrontPointer, headerType);

    Integer placeholderFrontPointer2 = 20;
    String headerType2 = "[[last_name]]";
    HeaderPair headerPair2 = new HeaderPair(placeholderFrontPointer2, headerType2);

    Integer placeholderFrontPointer3 = 98;
    String headerType3 = "[[first_name]]";
    HeaderPair headerPair3 = new HeaderPair(placeholderFrontPointer3, headerType3);

    Integer placeholderFrontPointer4 = 113;
    String headerType4 = "[[last_name]]";
    HeaderPair headerPair4 = new HeaderPair(placeholderFrontPointer4, headerType4);

    Integer placeholderFrontPointer5 = 127;
    String headerType5 = "[[address]]";
    HeaderPair headerPair5 = new HeaderPair(placeholderFrontPointer5, headerType5);

    List<HeaderPair> list = new ArrayList<>();
    list.add(headerPair);
    list.add(headerPair2);
    list.add(headerPair3);
    list.add(headerPair4);
    list.add(headerPair5);

    assertEquals(list.toString(), templateParser.getToBeReplaced().toString());
  }

  @Test
  public void equals() {
    assertTrue(templateParser.equals(templateParser));
  }

  @Test
  public void equals2() {
    assertFalse(templateParser.equals(headerPair));
  }

  @Test
  public void equals3() {
    assertFalse(templateParser.equals(null));
  }

  @Test
  public void equals4() {
    assertFalse(templateParser.equals("r"));
  }

  @Test
  public void equals5() {
    TemplateParser newParser = new TemplateParser();
    assertFalse(templateParser.equals(newParser));
  }

  @Test
  public void hashCode1() {
    assertFalse(templateParser.hashCode() == headerPair.hashCode());
  }

  @Test
  public void toString2() {
    assertEquals("This is a template parser.\n"
            + "places to be replaced are as follows:\n"
            + "[HeaderPair{placeholderFrontPointer=5, headerType='[[first_name]]'}, "
            + "HeaderPair{placeholderFrontPointer=20, headerType='[[last_name]]'}, "
            + "HeaderPair{placeholderFrontPointer=98, headerType='[[first_name]]'}, "
            + "HeaderPair{placeholderFrontPointer=113, headerType='[[last_name]]'}, "
            + "HeaderPair{placeholderFrontPointer=127, headerType='[[address]]'}]",
        templateParser.toString());
  }

}