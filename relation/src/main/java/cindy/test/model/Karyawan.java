package cindy.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "karyawan")
public class Karyawan{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @NotNull (message = "nama harus diisi.")
    @Column(name = "nama")
    private String name;

    @NotNull (message = "password harus diisi.")
    @Column(name = "idkaryawan")
    private String idkaryawan;

    @NotNull (message = "password harus diisi.")
    @Column(name = "shift")
    private String shift;


    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public String getIdkaryawan() {
        return idkaryawan;
    }

    public void setIdkaryawan(String idkaryawan) {
        this.idkaryawan= idkaryawan;
    }
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
   
    public Date getCreated_At() {
        return created_at;
    }

    public void setCreated_At(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_At() {
        return updated_at;
    }

    public void setUpdated_At(Date updated_at) {

        this.updated_at = updated_at;
    }

    public Date getDeleted_At() {
        return deleted_at;
    }
    
    public void setDeleted_At(Date deleted_at) {

        this.deleted_at = deleted_at;
    }

}