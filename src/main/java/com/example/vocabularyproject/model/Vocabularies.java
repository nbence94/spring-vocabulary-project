package com.example.vocabularyproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Vocabularies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vocabularies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String created;
    private String updated;

    @OneToMany(mappedBy = "vocabularies", cascade = CascadeType.ALL)
    List<Words> words;

    @Override
    public String toString() {
        return "Vocabularies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", words=" + words +
                '}';
    }
}
