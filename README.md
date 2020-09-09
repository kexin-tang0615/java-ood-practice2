This is an email and letter generator program which takes in an combination of 
arguments and generates emails and letters for customers. The generated emails and letters
are stored in the output directory user input. 

To build this program, we created these classes:

1. Flag enum class: It lists all type of legal input parameters. For example, "--email", "--letter".
2. CmdLineParser class: It takes an argument line and parses each piece of argument. Later on we can
get the information of csv path, output directory, etc.
3. CsvFileReader class: It takes in a csv file and extract customer information 
from it. It will return the csv file header as a string and all customer information
as list of string. The results will be consumed by CsvFileParser.
4. CsvFileParser class: It takes the output from CsvFileReader and parse these
information. It convert the header to a header value set and a list of map including header value
with their corresponding customer information.
5. TemplateFileReader class: it reads template file into string.
6. TemplateFileParser class: it scans through the template string and compare with csv header
set from CsvFileParser, which finally generates an index: list of pairs(location : value). With the
index, the later replacing would be fast.
7. TemplateFileGenerator class: it uses the output from TemplateFileParser and CsvFileParser 
class to replace necessary places and generates new emails and letters.
8. OutputWriter class: It outputs files with specific names and writes files to destination directory.
9. Main class: The program entrance. It takes input arguments and run through all above processes.
