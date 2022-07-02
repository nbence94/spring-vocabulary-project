package com.example.vocabularyproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="Words")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String english;
    private String hungarian;

    @ManyToOne
    @JoinColumn(name="vocabularyId", insertable = false, updatable = false)
    private Vocabularies vocabularies;

    private Integer vocabularyId;

    @JsonBackReference
    public Vocabularies getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(Vocabularies vocabularies) {
        this.vocabularies = vocabularies;
    }
}
