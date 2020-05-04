package com.techdev.sdg.PrivateSector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class PrivateSectorService {

    @Autowired
    private PrivateSectorRepository repository;

    public PrivateSector save(Map<String, Object> body) {
        PrivateSector ps = new PrivateSector(
                Objects.toString(body.get(PrivateSector.NAME), null),
                Objects.toString(body.get(PrivateSector.EMAIL), null),
                Objects.toString(body.get(PrivateSector.PASSWORD), null)
        );
        repository.save(ps);
        return ps;
    }
}
