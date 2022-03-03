package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Entity;


// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@Table(name="Student")
public class Student extends Person {
    @ManyToOne(targetEntity = GraduationClass.class, cascade = CascadeType.ALL)
    private GraduationClass belongTo;
    
    @OneToMany(targetEntity  = Grade.class)
    private List<Grade> grades;

    public GraduationClass getBelongTo() {
        return belongTo;
    }

    public Student setBelongTo(GraduationClass belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Student setGrades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }
}
