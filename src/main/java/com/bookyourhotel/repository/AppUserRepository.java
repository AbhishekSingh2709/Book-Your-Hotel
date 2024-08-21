package com.bookyourhotel.repository;

import com.bookyourhotel.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, String> {
    boolean existsByUsername(String username);
    boolean existsByEmailid(String email);
    Optional<AppUserEntity> findByUsername(String username);

}