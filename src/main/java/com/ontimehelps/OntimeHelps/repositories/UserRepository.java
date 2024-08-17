package com.ontimehelps.OntimeHelps.repositories;

import com.ontimehelps.OntimeHelps.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email , String password);

    List<User> findByNameContaining(String keyword);
}
