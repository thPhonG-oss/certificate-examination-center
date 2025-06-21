package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.ERole;
import com.pptk.certificate_examination_center.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
    @Query(value = "SELECT * FROM role",
    nativeQuery = true)
    Set<Role> findAllRoles();
}
