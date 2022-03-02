<<<<<<< HEAD
package fr.uga.im2ag.l3.miage.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
    @Type(type="float")
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
=======
package fr.uga.im2ag.l3.miage.db.model;

import javax.persistence.Column;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
public class Grade {

    private Long id;
    private Subject subject;
    @Column(name = "grade")
    private Float value;
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
>>>>>>> 01da5a4671cb1862f37c0b6c415f564ba7c9d013
