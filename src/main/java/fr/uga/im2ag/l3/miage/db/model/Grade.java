package fr.uga.im2ag.l3.miage.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@Table(name="Grade")
@NamedQueries({
   
    @NamedQuery(name = "Grade.findHighestGrades", query = "select G from Grade G order by G.value DESC"),
    @NamedQuery(name="Grade.findHighestGradesBySubject", query = "select G from Grade G where subjectGrade = :SubjectId order by G.value desc"),
    @NamedQuery(name="Grade.getAll", query = "select G from Grade G")
})
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

    @Column(name="gradeValue", updatable = false)
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
