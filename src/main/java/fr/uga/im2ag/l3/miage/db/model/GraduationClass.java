package fr.uga.im2ag.l3.miage.db.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// TODO ajouter une named query pour une des requêtes à faire dans le repository
@NamedQueries({
    @NamedQuery(name = "GraduationClass.getAll", query = "select g from GraduationClass g"),
    @NamedQuery(name = "GraduationClass.findByYearAndName", query = "select C from GraduationClass C where graduationClassName=:name and graduationClassYear=:year")
})

@Entity
@Table(name="GraduationClass")
public class GraduationClass {
    @Id
    @GeneratedValue
    @Column(name="graduationClassId")
    private Long id;

    @Column(name="graduationClassName", nullable=false, unique = true )
    private String name;
    @Column(name="graduationClassYear", nullable=false )
    private Integer year;

    @OneToMany(targetEntity = Student.class, mappedBy = "belongTo")
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GraduationClass setId(Long id) {
        this.id = id;
        return this;
    }

    public GraduationClass setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public GraduationClass setYear(Integer year) {
        this.year = year;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public GraduationClass setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
