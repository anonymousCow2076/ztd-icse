package com.anonuser.supporting;

import java.io.InputStream;

import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ext.DOMDeserializer;
import org.w3c.dom.Document;

public class VulnDomDeserializer extends DOMDeserializer<Person>{

    public VulnDomDeserializer() {
        super(Person.class);
    }

    public Person _deserialize(String value, DeserializationContext ctxt) {
        Document doc = this.parse(value);
        Person person = new Person();
        person.age = Integer.valueOf(doc.getElementsByTagName("age").item(0).getTextContent());
        person.name = doc.getElementsByTagName("name").item(0).getTextContent();
        return person;
    }

    public InputStream getResource(String name) {
        return this.getClass().getClassLoader().getResourceAsStream(name);
    }
}
