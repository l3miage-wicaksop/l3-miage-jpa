package fr.uga.im2ag.l3.miage.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@Table(name="Grade")
public class Grade {

    @Id
    @GeneratedValue
    @Column(name="gradeId")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(
        name = "subjectGrade",
        referencedColumnName = "subjectId",
        foreignKey = @ForeignKey(
            name = "FK_subject_grade"
        ) 
    )
    private Subject subject;

    @Column(name="gradeValue")
    private Float value;

    @Column(name="gradeWeight")
    private Float weight;

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Grade setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public Grade setValue(Float value) {
        this.value = value;
        return this;
    }

    public Float getWeight() {
        return weight;
    }

    public Grade setWeight(Float weight) {
        this.weight = weight;
        return this;
    }
}
