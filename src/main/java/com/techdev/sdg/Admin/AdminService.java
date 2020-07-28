package com.techdev.sdg.Admin;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.UserEntity;
import com.techdev.sdg.NGO.NGO;
import com.techdev.sdg.NGO.NGORepository;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.PrivateSector.PrivateSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminService {
    @Autowired
    private PrivateSectorRepository privateSectorRepository;

    @Autowired
    private AdminRepository repository;

    @Autowired
    private NGORepository ngoRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<Map<String, Object>> getSignupRequests() {
        List<PrivateSector> privateSectors = privateSectorRepository.findByIsApproved(false);
        List<NGO> ngos = ngoRepository.findByIsApproved(false);

        List<Entity> e = Stream.concat(privateSectors.stream(), ngos.stream())
                .collect(Collectors.toList());

        List<Map<String, Object>> entities = new ArrayList<>();

        for (Entity entity : e)
            entities.add(entity.toMap());
        return entities;
    }

    public void approveSignupRequest(Long id, String type) throws Exception {
        if (type.equals("PrivateSector")) {
            PrivateSector entity = privateSectorRepository.findById(id).get();
            entity.setIsApproved(true);
            privateSectorRepository.save(entity);
        }
        else if (type.equals("NGO")) {
            NGO entity = ngoRepository.findById(id).get();
            entity.setIsApproved(true);
            ngoRepository.save(entity);
        } else
            throw new Exception("Invalid entity type passed");
    }
    
    public Admin saveAdmin(Map<String, Object> body) {
        Admin admin = new Admin(Objects.toString(body.get(Admin.NAME), null),
                Objects.toString(body.get(Admin.EMAIL), null),
                bcryptEncoder.encode(Objects.toString(body.get(Admin.PASSWORD), null))
                );
        return repository.save(admin);
    }
    public List<Map<String, Object>> getAdmins() {
        List<Admin> admin = repository.findAll();
        List<Map<String, Object>> res = new ArrayList<>();
        for (Admin n : admin) {
            res.add(n.toMap());
        }
        return res;
    }
}
