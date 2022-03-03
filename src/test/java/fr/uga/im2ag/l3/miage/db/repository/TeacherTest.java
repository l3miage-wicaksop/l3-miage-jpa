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
        final var subject = Fixtures.createSubject();
        final var gradClass = Fixtures.createClass();

        final var student1 = Fixtures.createStudent(gradClass);
        final var student2 = Fixtures.createStudent(gradClass);

        Teacher teacher = Fixtures.createTeacher(subject, gradClass, student1, student2) ;



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
        // TODO
    }

}
