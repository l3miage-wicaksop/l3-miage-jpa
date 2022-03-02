package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Subject;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SubjectTest extends Base {

    SubjectRepository subjectRepository;

    @BeforeEach
    void before() {
        subjectRepository = daoFactory.newSubjectRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveSubject() {

        final var subject = Fixtures.createSubject();

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);

        var pSubject = subjectRepository.findById(subject.getId());
        assertThat(pSubject).isNotNull().isNotSameAs(subject);
        assertThat(pSubject.getName()).isEqualTo(subject.getName());

    }

    @Test
    void shouldFindTeachersForSubject() {
        
        final var subject1 = Fixtures.createSubject();
        final var subject2 = Fixtures.createSubject();
        final var subject3 = Fixtures.createSubject();

        

        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);

        entityManager.getTransaction().begin();
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        subjectRepository.save(subject3);
        entityManager.getTransaction().commit();
        entityManager.detach(subject1);
        entityManager.detach(subject2);
        entityManager.detach(subject3);

        
        
        // // a list of subjects
        List<Subject> subjects = new ArrayList<Subject>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        // // one graduation class
        final var class1 = Fixtures.createClass();
        entityManager.persist(class1);

        // // case of 2 students
        // final var student1 = Fixtures.createStudent(class1);
        // final var student2 = Fixtures.createStudent(class1);
        // entityManager.persist(student1);
        // entityManager.persist(student2);

        // // case of 2 teachers
        // final var teacher1 = Fixtures.createTeacher(subject1, class1, student1, student2);
        // final var teacher2 = Fixtures.createTeacher(subject2, class1, student2);

        // entityManager.persist(teacher1);
        // entityManager.persist(teacher2);

        // // a list of teachers
        // List<Teacher> teachers = new ArrayList<Teacher>();
        // teachers.add(teacher1);
        // teachers.add(teacher2);
        
        

        // for (Teacher teacher : teachers){
        //    var res = (Collection<Teacher>) subjectRepository.findTeachers(teacher.getId());
        //    assertThat(res).isNotNull();
        // }
        // TODO
    }

}
