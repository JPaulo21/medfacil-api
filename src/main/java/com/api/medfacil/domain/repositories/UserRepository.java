package com.api.medfacil.domain.repositories;

import com.api.medfacil.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCpf(String cpf);

    default Optional<User> findByNumber(String ddi, String ddd, String phoneNumber){
        return findByContactDdiAndContactDddAndContactPhoneNumber(ddi, ddd, phoneNumber);
    }

    Optional<User> findByContactDdiAndContactDddAndContactPhoneNumber(String ddi, String ddd, String phoneNumber);

}
