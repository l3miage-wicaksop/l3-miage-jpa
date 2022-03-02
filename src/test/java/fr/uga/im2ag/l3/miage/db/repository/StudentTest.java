package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        // TODO
        
        GraduationClass gclass1 = new GraduationClass();

        gclass1.setId((long) 2012);
        gclass1.setName("Alpha Beta Gamma");
        gclass1.setYear(2021);

        Student student1 = Fixtures.createStudent(gclass1);

        assertThat(student1).isNotNull();
    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        // TODO
    }

}
