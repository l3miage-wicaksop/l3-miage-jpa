package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Teacher;

import javax.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

public class SubjectRepositoryImpl extends BaseRepositoryImpl implements SubjectRepository {


    public SubjectRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Subject entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Subject entity) {
        entityManager.remove(entity);
    }

    @Override
    public Subject findById(Long id) {
        
        return (Subject) entityManager.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        // TODO
        return entityManager.createNamedQuery("select S from Subject", Subject.class).getResultList();
    }

    @Override
    public Collection<Teacher> findTeachers(Long id) {
        // TODO
        return entityManager.createNamedQuery("select name from Teacher where id=:id", Teacher.class).setParameter("id", id).getResultList();
    }
}
