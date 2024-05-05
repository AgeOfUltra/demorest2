package org.example.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Student {
    private int id;
    private String name;
    private String lang;
}
