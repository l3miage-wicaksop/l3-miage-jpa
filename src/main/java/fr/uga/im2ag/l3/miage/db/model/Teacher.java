package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQuery(name = "Teacher.findHeadingGraduationClassByYearAndName", query = "select T from Teacher T join T.heading GC where GC.name=:name and GC.year=:year")
@Entity
@Table(name="Teacher")
public class Teacher extends Person {
    @ManyToOne(targetEntity = Subject.class)
    private Subject teaching;
    @OneToMany(targetEntity = Student.class)
    private List<Student> favorites;
    @OneToOne(targetEntity = GraduationClass.class)
    private GraduationClass heading;

    public Subject getTeaching() {
        return teaching;
    }

    public Teacher setTeaching(Subject teaching) {
        this.teaching = teaching;
        return this;
    }

    public List<Student> getFavorites() {
        return favorites;
    }

    public Teacher setFavorites(List<Student> favorites) {
        this.favorites = favorites;
        return this;
    }

    public GraduationClass getHeading() {
        return heading;
    }

    public Teacher setHeading(GraduationClass heading) {
        this.heading = heading;
        return this;
    }

}
