package fr.uga.im2ag.l3.miage.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository

@NamedQuery(name = "Subject.getAll", query = "select s from Subject s")
@Entity
@Table(name = "Subject")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name="SubjectId")
    private Long id;

   
    @Column(name="subjectName", nullable = false, unique = true)
    private String name;

    @Column(name="subjectPoints")
    private Integer points;

    @Column(name="subjectHours", nullable = false)
    private Float hours;

    @Column(name="subjectStart", nullable = false)
    private Date start;

    @Column(name="subjectEnd" , nullable = false)
    private Date end;

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public Subject setPoints(Integer points) {
        this.points = points;
        return this;
    }

    public Float getHours() {
        return hours;
    }

    public Subject setHours(Float hours) {
        this.hours = hours;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public Subject setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public Subject setEnd(Date end) {
        this.end = end;
        return this;
    }
}
