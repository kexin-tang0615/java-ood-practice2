package edu.neu.khoury.cs5004.assignment8;

/**
 * The enum Flag.
 */
public enum Flag {
  /**
   * Email flag.
   */
  EMAIL("--email"),
  /**
   * Email template flag.
   */
  EMAIL_TEMPLATE("--email-template"),
  /**
   * Letter flag.
   */
  LETTER("--letter"),
  /**
   * Letter template flag.
   */
  LETTER_TEMPLATE("--letter-template"),
  /**
   * Output dir flag.
   */
  OUTPUT_DIR("--output-dir"),
  /**
   * Csv file flag.
   */
  CSV_FILE("--csv-file");

  private String value;

  Flag(String value) {
    this.value = value;
  }

  /**
   * Check flag type flag.
   *
   * @param value the value
   * @return the flag
   */
  static Flag checkFlagType(String value) {
    if (EMAIL.value.equals(value)) {
      return EMAIL;
    }
    if (EMAIL_TEMPLATE.value.equals(value)) {
      return EMAIL_TEMPLATE;
    }
    if (LETTER.value.equals(value)) {
      return LETTER;
    }
    if (LETTER_TEMPLATE.value.equals(value)) {
      return LETTER_TEMPLATE;
    }
    if (OUTPUT_DIR.value.equals(value)) {
      return OUTPUT_DIR;
    }
    if (CSV_FILE.value.equals(value)) {
      return CSV_FILE;
    }
    return null;
  }
}
