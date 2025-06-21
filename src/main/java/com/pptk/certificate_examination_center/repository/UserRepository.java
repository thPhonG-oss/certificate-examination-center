package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.entity.Role;
import com.pptk.certificate_examination_center.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT r.id, r.name" +
            " FROM [user] u JOIN user_role ur ON u.id = ur.user_id" +
            " JOIN role r ON ur.role_id = r.id",
    nativeQuery = true)
    public Set<Role> findRolesByUserId(@Param("userId") Long userId);

    @Query(value = "INSERT INTO user_role(user_id, role_id)" +
            " VALUES (:user_id, :role_id)",
    nativeQuery = true)
    public void addUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id);
}
