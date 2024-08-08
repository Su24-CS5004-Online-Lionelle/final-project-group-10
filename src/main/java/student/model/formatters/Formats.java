package student.model.formatters;

/**
 * An enum class that represents different formatting options.
 */
public enum Formats {
    /** Different formatting options. */
    JSON, XML, CSV, TXT;

    /**
     * Helper function to check if a value is in the list of formats.
     *
     * @param value the value to check
     * @return the format if found, null otherwise
     */
    public static Formats containsValues(String value) {
        for (Formats format : Formats.values()) {
            if (format.toString().equalsIgnoreCase(value)) {
                return format;
            }
        }
        return null;
    }
}
