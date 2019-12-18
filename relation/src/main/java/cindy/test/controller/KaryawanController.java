package cindy.test.controller;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import cindy.test.model.Karyawan;
import cindy.test.repository.KaryawanInterface;

@Controller("/karyawan")
public class KaryawanController{

    private KaryawanInterface repository;
    
    KaryawanController(KaryawanInterface r){
        this.repository = r;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public String index(@QueryValue int page, @QueryValue int limit) {
        List<Karyawan> karyawan = repository.findAll(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("page", Math.ceil(repository.size() / limit));
        data.put("data", karyawan);
        return (new Gson()).toJson(data);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable Long id) {
        return (new Gson()).toJson(repository.findById(id));
    }

    @Post(consumes=MediaType.APPLICATION_JSON)
    public String save(@Body Karyawan t) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.save(t)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Put(consumes=MediaType.APPLICATION_JSON)
    public String update(@Body Karyawan c) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.update(c.getId(), c.getName(), c.getIdkaryawan(), c.getShift())) {
        // if (repository.updateCode(c.getId(), c.getCode())) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }

    @Delete("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String destroy(@PathVariable Long id) {
        HashMap<String, Object> data = new HashMap<>();
        if (repository.destroy(id)) {
            data.put("status", "ok");
        } else {
            data.put("status", "fail");
        }
        return (new Gson()).toJson(data);
    }
}