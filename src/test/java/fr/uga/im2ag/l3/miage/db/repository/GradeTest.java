package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GradeTest extends Base {

    GradeRepository gradeRepository;

    @BeforeEach
    void before() {
        gradeRepository = daoFactory.newGradeRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveGrade() {
        
        // heavily inspired by the example from SubjectTest.java
        final var subject = Fixtures.createSubject();
        final var grade = Fixtures.createGrade(subject);

        entityManager.getTransaction().begin();
        entityManager.persist(subject); // need to persist the subject to avoid error
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        entityManager.detach(grade);

        var pGrade = gradeRepository.findById(grade.getId());
        assertThat(pGrade).isNotNull().isNotEqualTo(grade);
        assertThat(pGrade.getId()).isEqualTo(grade.getId());
        
    }

    @Test
    //ici tester que la mise à jour n'a pas eu lieu.
    void shouldFailUpgradeGrade() {
        final var subject = Fixtures.createSubject();
        final var grade = Fixtures.createGrade(subject);

        float oldGrade = grade.getValue();
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        gradeRepository.save(grade);
        gradeRepository.findById(grade.getId()).setValue(2000f); // updating value after saved
        entityManager.getTransaction().commit();
        entityManager.detach(grade);

        
        var result2 = gradeRepository.findById(grade.getId());
        
        assertThat(result2.getValue()).isEqualTo(oldGrade);
        assertThat(result2.getValue()).isNotEqualTo(2000f);
        // value isn't changed and still the same as oldGrade
    }

    @Test
    void shouldFindHighestGrades() {
        final var subject = Fixtures.createSubject();
        final var grade1 = Fixtures.createGrade(subject);
        final var grade2 = Fixtures.createGrade(subject);
        final var grade3 = Fixtures.createGrade(subject);

        entityManager.getTransaction().begin();
        entityManager.persist(subject); // need to persist the subject to avoid error
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        entityManager.getTransaction().commit();
        
        grade1.setValue((float) 10.5);
        grade2.setValue((float) 12.5);
        grade3.setValue((float) 19.5);

        var result = gradeRepository.findHighestGrades(1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getValue()).isEqualTo(grade3.getValue());
        

    }

    @Test
    void shouldFindHighestGradesBySubject() {

        final var subject1 = Fixtures.createSubject();
        final var subject2 = Fixtures.createSubject();


        final var grade1Subject1 = Fixtures.createGrade(subject1);
        final var grade2Subject1 = Fixtures.createGrade(subject1);
        final var grade3Subject1 = Fixtures.createGrade(subject1);
        
        final var grade1Subject2 = Fixtures.createGrade(subject2);
        final var grade2Subject2 = Fixtures.createGrade(subject2);
        final var grade3Subject2 = Fixtures.createGrade(subject2);

        
        grade1Subject1.setValue((float) 7);
        grade2Subject1.setValue((float) 18);
        grade3Subject1.setValue((float) 19);

        grade1Subject2.setValue((float) 1.2);
        grade2Subject2.setValue((float) 18.1);
        grade3Subject2.setValue((float) 19.7);


        entityManager.getTransaction().begin();
        entityManager.persist(subject1); // need to persist the subject to avoid error
        entityManager.persist(subject2); // need to persist the subject to avoid error
        gradeRepository.save(grade1Subject1);
        gradeRepository.save(grade2Subject1);
        gradeRepository.save(grade3Subject1);
        
        gradeRepository.save(grade1Subject2);
        gradeRepository.save(grade2Subject2);
        gradeRepository.save(grade3Subject2);
        
        entityManager.getTransaction().commit();

        var limit = 1;
        var resultSubject1 = gradeRepository.findHighestGradesBySubject(limit, subject1);

        assertThat(resultSubject1).isNotNull();
        assertThat(resultSubject1.size()).isEqualTo(limit);
        assertThat(resultSubject1.get(0).getValue()).isEqualTo(grade3Subject1.getValue());

        var resultSubject2 = gradeRepository.findHighestGradesBySubject(limit, subject2);

        assertThat(resultSubject2).isNotNull();
        assertThat(resultSubject2.size()).isEqualTo(limit);
        assertThat(resultSubject2.get(0).getValue()).isEqualTo(grade3Subject2.getValue());

    }

}
