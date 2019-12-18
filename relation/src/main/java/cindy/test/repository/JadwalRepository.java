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
import cindy.test.model.Jadwal;

@Singleton
public class JadwalRepository implements JadwalInterface {

    @PersistenceContext
    private EntityManager manager;

    public JadwalRepository(@CurrentSession EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Jadwal where deleted_at IS NULL", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional
    public List<Jadwal> findAll(int page, int limit) {
        TypedQuery<Jadwal> query = manager
                .createQuery("from Jadwal where deleted_at IS NULL", Jadwal.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Jadwal findById(@NotNull Long id) {
        Jadwal query = manager.find(Jadwal.class, id);
        return query;
    }

    @Override
    @Transactional
    public boolean save(@NotNull Jadwal jadwal) {
        try {
            manager.persist(jadwal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(@NotNull Long id, String hari, String jam_masuk, String jam_pulang) {
        try {

            Jadwal c = manager.find(Jadwal.class, id);
            if (hari.equals("-")==false) c.setHari(hari);
            if (jam_masuk.equals("-")==false) c.setJammasuk(jam_masuk);
            if (jam_pulang.equals("-")==false) c.setJamPulang(jam_pulang);
          
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
            Jadwal c = manager.find(Jadwal.class, id);
            c.setDeleted_At(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}