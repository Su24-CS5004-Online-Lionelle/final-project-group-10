package student.model.formatters;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import student.model.ICharacter.CharacterRecord;
import java.util.Collection;

/**
 * Wrapper class for XML serialization.
 */
@JacksonXmlRootElement(localName = "characterList")
public final class DomainXmlWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    private Collection<CharacterRecord> domain;

    /**
     * Constructor for the DomainXmlWrapper.
     *
     * @param characters The list of character records to wrap.
     */
    public DomainXmlWrapper(Collection<CharacterRecord> characters) {
        this.domain = characters;
    }
}
