package com.techdev.sdg.Admin;

import com.techdev.sdg.Entity.Entity;
import com.techdev.sdg.Entity.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminService {
    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private AdminRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<Map<String, Object>> getSignupRequests() {
        List<Entity> entities = entityRepository.findByIsApproved(false);

        List<Map<String, Object>> entityObjects = new ArrayList<>();

        for (Entity entity : entities)
            entityObjects.add(entity.toMap());
        return entityObjects;
    }

    public void approveSignupRequest(Long id) throws Exception {
        Entity entity = entityRepository.findById(id).get();
        entity.setIsApproved(true);
        entityRepository.save(entity);
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

    public void deleteAdmin(Long id) throws Exception {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}
