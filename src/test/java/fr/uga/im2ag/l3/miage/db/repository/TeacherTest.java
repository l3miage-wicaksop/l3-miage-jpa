package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacherTest extends Base {

    TeacherRepository teacherRepository;

    @BeforeEach
    void before() {
        teacherRepository = daoFactory.newTeacherRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveTeacher()  {
        
        final var gradClass = Fixtures.createClass();
        final var subject = Fixtures.createSubject();
        final var student1 = Fixtures.createStudent(gradClass);
        final var student2 = Fixtures.createStudent(gradClass);

        final var teacher = Fixtures.createTeacher(subject, gradClass, student1, student2) ;

        entityManager.persist(subject);
        entityManager.persist(gradClass);
        entityManager.persist(student1);
        entityManager.persist(student2);

        entityManager.getTransaction().begin();
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        entityManager.detach(teacher);

        var result = teacherRepository.findById(teacher.getId());

        assertThat(result).isNotEqualTo(teacher);
        assertThat(result.getId()).isEqualTo(teacher.getId());
        

    }

    @Test
    void shouldFindHeadingGraduationClassByYearAndName() {
        final var gradClass = Fixtures.createClass();
        final var subject = Fixtures.createSubject();
        final var student1 = Fixtures.createStudent(gradClass);
        final var student2 = Fixtures.createStudent(gradClass);

        final var teacher = Fixtures.createTeacher(subject, gradClass, student1, student2) ;

        entityManager.persist(subject);
        entityManager.persist(gradClass);
        entityManager.persist(student1);
        entityManager.persist(student2);

        entityManager.getTransaction().begin();
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        entityManager.detach(teacher);

        var result = teacherRepository.findHeadingGraduationClassByYearAndName(teacher.getHeading().getYear(), teacher.getHeading().getName());

        assertThat(result).isNotEqualTo(teacher);
        assertThat(result.getFirstName()).isEqualTo(teacher.getFirstName());     

    }

}
