package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@NamedQueries({
    @NamedQuery(name = "Student.getAll", query = "select S from Student S"),
    @NamedQuery(name = "Student.findStudentHavingGradeAverageAbove", query = "select S from Student S JOIN S.grades G group by S.id having sum(G.value*G.weight)/sum(G.weight) >= :minAverage")
})
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
