package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Subject;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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
        // declaring objects
        final var subject = Fixtures.createSubject();
        final var gradClass = Fixtures.createClass();
        final var student1 = Fixtures.createStudent(gradClass);
        final var student2 = Fixtures.createStudent(gradClass);


       

        // declaring teachers
        final var teacher1 = Fixtures.createTeacher(subject, gradClass, student1, student2) ;
        final var teacher2 = Fixtures.createTeacher(subject, gradClass, student1, student2) ;

        // persisting things
 

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);
        // entityManager.detach(subject);

        var results = subjectRepository.findById(subject.getId());

        
        assertThat(results.getId()).isEqualTo(teacher1.getTeaching().getId());
        assertThat(results.getId()).isEqualTo(teacher2.getTeaching().getId());
        



        
    }

}
