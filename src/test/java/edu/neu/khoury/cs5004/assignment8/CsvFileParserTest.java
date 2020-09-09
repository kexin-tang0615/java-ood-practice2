package edu.neu.khoury.cs5004.assignment8;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CsvFileParserTest {

  private String header;
  private List<String> contents;
  private List<Map<String, String>> listOfMap;
  private CsvFileParser csvFileParser;
  private CsvFileParser csvFileParser2;

  @Before
  public void setUp() throws Exception {
    String csvFile = "files_for_test/csv_file.csv";
    CsvFileReader fileReader = new CsvFileReader(csvFile);
    Map<String, String> infoMap = new HashMap<>();
    Map<String, String> infoMap2 = new HashMap<>();
    listOfMap = new ArrayList<>();
    csvFileParser = new CsvFileParser();
    csvFileParser2 = new CsvFileParser();
    header = fileReader.readCsvFileHeader();
    contents = fileReader.readCsvFileContent();
    infoMap.put("[[first_name]]", "James");
    infoMap.put("[[last_name]]", "Butt");
    infoMap.put("[[company_name]]", "Benton, John B Jr");
    infoMap.put("[[address]]", "6649 N Blue Gum St");
    infoMap2.put("[[first_name]]", "Josephine");
    infoMap2.put("[[last_name]]", "Darakjy");
    infoMap2.put("[[company_name]]", "Chanay, Jeffrey A Esq");
    infoMap2.put("[[address]]", "4 B Blue Ridge Blvd");
    listOfMap.add(infoMap);
    listOfMap.add(infoMap2);
  }

  @Test
  public void testConstructHeaderSet() {
    assertEquals("[[[first_name]], [[address]], [[last_name]], [[company_name]]]",
        csvFileParser.constructHeaderSet(header).toString());

  }

  @Test
  public void testParseCsvFile() throws IOException {
    assertEquals(listOfMap, csvFileParser.parseCsvFile(contents, header));
  }

  @Test
  public void testEquals() {
    assertTrue(csvFileParser.equals(csvFileParser));
  }

  @Test
  public void testEquals2() {
    assertTrue(csvFileParser.equals(csvFileParser2));
  }

  @Test
  public void testEquals3() {
    assertFalse(csvFileParser.equals(null));
  }

  @Test
  public void testEquals4() {
    assertFalse(csvFileParser.equals("a"));
  }

  @Test
  public void testHashCode() {
    assertFalse(csvFileParser.hashCode() == listOfMap.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("This is a csv file parser!", csvFileParser.toString());
  }
}