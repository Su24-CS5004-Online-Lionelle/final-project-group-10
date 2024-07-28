package student.model.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import student.model.ICharacter.CharacterRecord;

import javax.annotation.Nonnull;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;

/**
 * A class to format the data into XML, CSV, JSON, or TXT files.
 * This is only used for File Output!
 * Displaying the information in the JFrame will be handled separately in the View class.
 **/
public final class DataFormatter {

    /**
     * Private constructor to prevent instantiation.
     */
    private DataFormatter() {
        // empty
    }

    /**
     * Print the characters in a human-readable format.
     *
     * @param characters the character records to print.
     * @return String the formatted string of the records.
     */
    public static void txtPrint(Collection<CharacterRecord> characters, OutputStream out) {
        PrintStream ps = new PrintStream(out);
        for (CharacterRecord character : characters) {
            txtPrintSingle(character, ps);
            ps.println();
        }
    }

    /**
     * Print a single character record.
     *
     * @param character the character record to print
     * @param ps        the print stream to write to
     */
    private static void txtPrintSingle(CharacterRecord character, @Nonnull PrintStream ps) {
        ps.println("Name: " + character.name());
        ps.println("Status: " + character.status());
        ps.println("Species: " + character.species());
        ps.println("Gender: " + character.gender());
        ps.println("Image: " + character.image());
        ps.println("Episodes: " + String.join(", ", character.episode()));
    }

    /**
     * Write the data in XML format.
     *
     * @param characters the records to write
     * @return a String representation of the csv data
     */
    public static void writeXmlData(Collection<CharacterRecord> characters, OutputStream out) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            DomainXmlWrapper wrapper = new DomainXmlWrapper(characters);
            xmlMapper.writeValue(out, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the data in JSON format.
     *
     * @param characters the records to write
     * @return a String representation of the csv data
     */
    public static void writeJsonData(Collection<CharacterRecord> characters, OutputStream out) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(out, characters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the data in CSV format.
     *
     * @param characters the records to write
     * @return a String representation of the csv data
     */
    public static void writeCSVData(Collection<CharacterRecord> characters, OutputStream out) {
        String[] header = {
                "name",
                "status",
                "species",
                "gender",
                "imageurl",
                "episodes"
        };
        try {
            out.write(String.join(",", header).getBytes());
            out.write("\n".getBytes());

            for (CharacterRecord character : characters) {
                String[] fields = {
                        character.name(),
                        character.status(),
                        character.species(),
                        character.gender(),
                        character.image(),
                        String.join(",", character.episode()),
                };
                out.write(String.join(",", fields).getBytes());
                out.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        String[] header = {"hostname","ip","city","region","country",
//                "postal","latitude","longitude"};
//        try{
//            out.write(String.join(",",header).getBytes());
//            out.write("\n".getBytes());
//            for (CharacterRecord character : characters) {
//                String[] fields = {
//                        character.name(),
//                        character.status(),
//                        character.species(),
//                        character.gender(),
//                        character.image(),
//                        String.join(",", character.episode()),
//                };
//                out.write(String.join(",", fields).getBytes());
//                out.write("\n".getBytes());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * Write the data in the specified format.
     *
     * @param characters the records to write
     * @param format     the format to write the records in
     * @return the formatted string of the reords
     */
    public static void write(@Nonnull Collection<CharacterRecord> characters, @Nonnull Formats format, OutputStream out) {
        switch (format) {
            case XML:
                writeXmlData(characters, out);
                break;
            case JSON:
                writeJsonData(characters, out);
                break;
            case CSV:
                writeCSVData(characters, out);
                break;
            case TXT:
                txtPrint(characters, out);
                break;
            default:
                throw new IllegalArgumentException("Invalid format"); // should never reach here
        }

    }


}
