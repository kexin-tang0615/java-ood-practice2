package edu.neu.khoury.cs5004.assignment8;

import java.util.Objects;

/**
 * Abstract class AFileReader contains information about a file reader.
 */
public abstract class AFileReader {

  private String fileName;

  /**
   * Constructor that creates a new AFileReader object, based upon all of the provided input
   * parameters.
   *
   * @param fileName the file name
   */
  public AFileReader(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Gets file name.
   *
   * @return the file name
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns the boolean value whether this equals to the given obj.
   *
   * @return the boolean value whether this equals to the given obj
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    AFileReader that = (AFileReader) obj;
    return Objects.equals(getFileName(), that.getFileName());
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getFileName());
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a file reader ";
  }
}
