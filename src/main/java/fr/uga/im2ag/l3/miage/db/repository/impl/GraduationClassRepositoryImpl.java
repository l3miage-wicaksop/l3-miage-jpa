package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;

import javax.persistence.EntityManager;
import java.util.List;

public class GraduationClassRepositoryImpl extends BaseRepositoryImpl implements GraduationClassRepository {

    public GraduationClassRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public GraduationClass findByYearAndName(Integer year, String name) {
        
        return entityManager.createNamedQuery("GraduationClass.findByYearAndName", GraduationClass.class)
                .setParameter("name", name)
                .setParameter("year", year).getSingleResult();

    }

    @Override
    public void save(GraduationClass entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(GraduationClass entity) {
        entityManager.remove(entity);
    }

    @Override
    public GraduationClass findById(Long id) {
        
        return entityManager.find(GraduationClass.class, id);
    }

    @Override
    public List<GraduationClass> getAll() {
        
        return entityManager.createQuery("select G from GraduationClass G", GraduationClass.class).getResultList();
    }
}
