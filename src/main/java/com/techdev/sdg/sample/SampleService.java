package com.techdev.sdg.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class SampleService {
    @Autowired
    private SampleRepository repository;

    public SampleModel save(Map<String, Object> body) throws Exception {
        // Objects.toString() is used as opposed to String.valueOf and .toString() because they return
        // a string with a value of null, this make mysql think it is not a null value
        // Objects.toString() allows you to set the default null value you would like
        SampleModel s = new SampleModel(
                Objects.toString(body.get(SampleModel.USERNAME), null),
                Objects.toString(body.get(SampleModel.PASSWORD), null)
        );

        repository.save(s);
        return s;
    }

    public Optional<SampleModel> getById(Long id) throws Exception {
        Optional<SampleModel> s = repository.findById(id);
        if (s.isEmpty())
            throw new Exception("User with the specified id does not exist");
        return s;
    }
}
