package cindy.test.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.test.model.Karyawan;

public interface KaryawanInterface {

    Long size();
    List<Karyawan> findAll (int page, int limit);
    Karyawan findById (@NotNull Long id);
    boolean save(@NotNull Karyawan karyawan);
    boolean update(@NotNull Long id, @NotBlank String name, @NotBlank String idkaryawan,@NotBlank String shift); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}