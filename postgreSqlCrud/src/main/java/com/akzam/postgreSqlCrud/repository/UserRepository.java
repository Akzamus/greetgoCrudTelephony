package com.akzam.postgreSqlCrud.repository;

import com.akzam.postgreSqlCrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM users OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<User> findAllWithOffsetAndLimit(long offset, long limit);

}
