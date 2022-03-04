package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
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
        
        
        final var gclass1 = new GraduationClass();

        
        gclass1.setName("Alpha Beta Gamma");
        gclass1.setYear(2021);

        final var student1 = Fixtures.createStudent(gclass1);
        final var student2 = Fixtures.createStudent(gclass1);

        entityManager.persist(student1);
        entityManager.persist(student2);

        
        entityManager.getTransaction().begin();
        classRepository.save(gclass1);
        entityManager.getTransaction().commit();
        entityManager.detach(gclass1);

        var result = classRepository.findById(gclass1.getId());
        assertThat(result).isNotNull().isNotSameAs(gclass1);
        assertThat(result.getName()).isEqualTo(gclass1.getName());

    }


    @Test
    void shouldFindByYearAndName() {
        final var gclass1 = new GraduationClass();

        
        gclass1.setName("Alpha Beta Gamma");
        gclass1.setYear(2021);

        final var student1 = Fixtures.createStudent(gclass1);
        final var student2 = Fixtures.createStudent(gclass1);

        
        entityManager.persist(student1);
        entityManager.persist(student2);

        entityManager.getTransaction().begin();
        classRepository.save(gclass1);
        entityManager.getTransaction().commit();
        entityManager.detach(gclass1);

        var result = classRepository.findByYearAndName(2021, "Alpha Beta Gamma" );

        assertThat(result).isNotNull().isNotEqualTo(gclass1);
        assertThat(result.getId()).isEqualTo(gclass1.getId());

    }

}
