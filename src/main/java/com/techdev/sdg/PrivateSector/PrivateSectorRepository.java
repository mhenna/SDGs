package com.techdev.sdg.PrivateSector;

import com.techdev.sdg.NGO.NGO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PrivateSectorRepository extends JpaRepository<PrivateSector, Long> {
    PrivateSector findByName (String name);

    List<PrivateSector> findByIsApproved(Boolean isApproved);

    @Transactional
    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Query("update PrivateSector set isApproved = :isApproved WHERE name = :name")
    void modifyApproveState(@Param("isApproved") Boolean isApproved,
                                   @Param("name") String name);
}
