package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class StudentTest extends Base {

    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        studentRepository = daoFactory.newStudentRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveStudent() {
        
        GraduationClass gclass1 = new GraduationClass();

        
        gclass1.setName("Alpha Beta Gamma");
        gclass1.setYear(2021);

        Student student1 = Fixtures.createStudent(gclass1);

        assertThat(student1).isNotNull();

        entityManager.getTransaction().begin();
        studentRepository.save(student1);
        entityManager.getTransaction().commit();
        entityManager.detach(student1);

        var result = studentRepository.findById(student1.getId());
    
        assertThat(result).isNotNull().isNotEqualTo(student1);
        assertThat(result.getId()).isEqualTo(student1.getId());
    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        
        // declaring graduation class
        GraduationClass gclass1 = Fixtures.createClass();
        
        Student student1 = Fixtures.createStudent(gclass1); // case of moyenne 10
        Student student2 = Fixtures.createStudent(gclass1); // case of moyenne <10
        Student student3 = Fixtures.createStudent(gclass1); // case of moyenne >10

        // student 1 grades declarations 
        // valeur moyenne 10
        final var student1Grade1 = Fixtures.createGrade(null).setValue(10f).setWeight(0.3f);
        final var student1Grade2 = Fixtures.createGrade(null).setValue(10f).setWeight(0.3f);
        final var student1Grade3 = Fixtures.createGrade(null).setValue(10f).setWeight(0.2f);
        final var student1Grade4 = Fixtures.createGrade(null).setValue(10f).setWeight(0.2f);

        student1.setGrades(Arrays.asList(student1Grade1, student1Grade2, student1Grade3, student1Grade4));

        // student 2 grades declarations 
        // valeur moyenne < 10
        final var student2Grade1 = Fixtures.createGrade(null).setValue(4f).setWeight(0.3f);
        final var student2Grade2 = Fixtures.createGrade(null).setValue(2f).setWeight(0.3f);
        final var student2Grade3 = Fixtures.createGrade(null).setValue(8f).setWeight(0.2f);
        final var student2Grade4 = Fixtures.createGrade(null).setValue(11f).setWeight(0.2f);

        student2.setGrades(Arrays.asList(student2Grade1, student2Grade2, student2Grade3, student2Grade4));

        // student 3 grades declarations 
        // valeur moyenne > 10
        final var student3Grade1 = Fixtures.createGrade(null).setValue(14f).setWeight(0.3f);
        final var student3Grade2 = Fixtures.createGrade(null).setValue(12f).setWeight(0.3f);
        final var student3Grade3 = Fixtures.createGrade(null).setValue(18f).setWeight(0.2f);
        final var student3Grade4 = Fixtures.createGrade(null).setValue(19f).setWeight(0.2f);

        student3.setGrades(Arrays.asList(student3Grade1, student3Grade2, student3Grade3, student3Grade4));

        // persisting those grades
        entityManager.persist(student1Grade1);
        entityManager.persist(student1Grade2);
        entityManager.persist(student1Grade3);
        entityManager.persist(student1Grade4);
        entityManager.persist(student2Grade1);
        entityManager.persist(student2Grade2);
        entityManager.persist(student2Grade3);
        entityManager.persist(student2Grade4);
        entityManager.persist(student3Grade1);
        entityManager.persist(student3Grade2);
        entityManager.persist(student3Grade3);
        entityManager.persist(student3Grade4);

        // saving students
        entityManager.getTransaction().begin();
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        entityManager.getTransaction().commit();
        entityManager.detach(student1);
        entityManager.detach(student2);
        entityManager.detach(student3);

        // sending request
        Float limitMoyenne = 10f;
        var result = studentRepository.findStudentHavingGradeAverageAbove(limitMoyenne);

        assertThat(result).isNotNull();
        
        assertThat(result.get(0)).isNotEqualTo(student1);
        assertThat(result.get(0).getFirstName()).isEqualTo(student1.getFirstName());
        
        assertThat(result.get(0)).isNotEqualTo(student3);
        assertThat(result.get(1).getFirstName()).isEqualTo(student3.getFirstName());
        

    }

}
