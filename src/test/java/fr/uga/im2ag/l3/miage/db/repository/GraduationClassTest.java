package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GraduationClassTest extends Base {

    GraduationClassRepository classRepository;

    @BeforeEach
    void before() {
        classRepository = daoFactory.newGraduationClassRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveClass() {
        // TODO
        
        
        GraduationClass gclass1 = new GraduationClass();

        gclass1.setId((long) 2012);
        gclass1.setName("Alpha Beta Gamma");
        gclass1.setYear(2021);

        Student student1 = Fixtures.createStudent(gclass1);
        Student student2 = Fixtures.createStudent(gclass1);

        
        

        assertThat(gclass1).isNotNull();
        assertThat(student1).isNotNull();
        assertThat(student2).isNotNull();

    }


    @Test
    void shouldFindByYearAndName() {
        // TODO
    }

}
