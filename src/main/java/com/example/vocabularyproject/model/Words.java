package com.example.vocabularyproject.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private int id;
    private String english;
    private String hungarian;

    @ManyToOne
    @JoinColumn(name="vocabularyId", insertable = false, updatable = false)
    private Vocabularies vocabularies;

    private int vocabularyId;
}
