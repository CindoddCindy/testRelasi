package cindy.test.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import cindy.test.model.Karyawan;

@Singleton
public class KaryawanRepository implements KaryawanInterface {

    @PersistenceContext
    private EntityManager manager;

    public KaryawanRepository(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Karyawan where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Karyawan> findAll(int page, int limit) {
        TypedQuery<Karyawan> query = manager
                .createQuery("from Karyawan where deleted_at IS NULL", Karyawan.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Karyawan findById(@NotNull Long id) {
        Karyawan query = manager.find(Karyawan.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Karyawan karyawan) {
        try {
            manager.persist(karyawan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String name, String idkaryawan, String shift) {
        try {

            Karyawan c = manager.find(Karyawan.class, id);
            if (name.equals("-")==false) c.setName(name);
            if (idkaryawan.equals("-")==false) c.setIdkaryawan(idkaryawan);
            if (shift.equals("-")==false) c.setShift(shift);
         
          //  if (grade != 0) c.setGrade(grade);
            c.setUpdated_At(new Date());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            Karyawan c = manager.find(Karyawan.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}