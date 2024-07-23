package student.model.formatters;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import student.model.ICharacter.CharacterRecord;

/**
 * A class to format the data into XML, CSV, JSON, or TXT files.
 * This is only used for File Output!
 * Displaying the information in the JFrame will be handled separately in the View class.
 * **/
public final class DataFormatter {

    /**
     * Private constructor to prevent instantiation.
     */
    private DataFormatter() {
        // empty
    }

    /**
     * Print the characters in a human-readable format.
     * @param characters the character records to print.
     * @return String the formatted string of the records.
     */
    public static String txtPrint(Collection<CharacterRecord> characters) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        for (CharacterRecord character : characters) {
            txtPrintSingle(character, ps);
            ps.println();
        }
        return baos.toString();
    }

    /**
     * Print a single character record.
     *
     * @param character the character record to print
     * @param ps the print stream to write to
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
    public static String writeXmlData(Collection<CharacterRecord> characters) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            DomainXmlWrapper wrapper = new DomainXmlWrapper(characters);
            xmlMapper.writeValue(baos, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    /**
     * Write the data in JSON format.
     *
     * @param characters the records to write
     * @return a String representation of the csv data
     */
    public static String writeJsonData(Collection<CharacterRecord> characters) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(baos, characters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    /**
     * Write the data in CSV format.
     *
     * @param characters the records to write
     * @return a String representation of the csv data
     */
    public static String writeCSVData(Collection<CharacterRecord> characters) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(baos);
        String[] header = {
                "name",
                "status",
                "species",
                "gender",
                "imageurl",
                "episodes"
        };
        writer.println(String.join(",", header));
        for (CharacterRecord character : characters) {
            String[] fields = {
                    character.name(),
                    character.status(),
                    character.species(),
                    character.gender(),
                    character.image(),
                    String.join(",", character.episode()),
            };
            writer.println(String.join(",", fields));
        }
        return baos.toString();
    }

    /**
     * Write the data in the specified format.
     *
     * @param characters the records to write
     * @param format the format to write the records in
     * @return the formatted string of the reords
     */
    public static String write(@Nonnull Collection<CharacterRecord> characters, @Nonnull Formats format) {
        switch (format) {
            case XML:
                return writeXmlData(characters);
            case JSON:
                return writeJsonData(characters);
            case CSV:
                return writeCSVData(characters);
            case TXT:
                return txtPrint(characters);
            default:
                break; // should never reach here
        }
        throw new IllegalArgumentException("Invalid format"); // should never reach here
    }
}
