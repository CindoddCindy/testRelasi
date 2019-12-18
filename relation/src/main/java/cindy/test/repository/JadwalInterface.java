package cindy.test.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cindy.test.model.Jadwal;

public interface JadwalInterface {

    Long size();
    List<Jadwal> findAll (int page, int limit);
    Jadwal findById (@NotNull Long id);
    boolean save(@NotNull Jadwal jadwal);
    boolean update(@NotNull Long id, @NotBlank String hari, @NotBlank String jam_masuk, @NotBlank String jam_pulang); // @NotNull int grade);
    boolean destroy(@NotNull Long id);
}