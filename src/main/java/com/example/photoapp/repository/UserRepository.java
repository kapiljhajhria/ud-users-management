package com.example.photoapp.repository;

import com.example.photoapp.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findUserByEmail(String email);

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findUserByEmailVerificationToken(String token);

    @Query(value = "select * from users u where u.EMAIL_VERIFICATION_STATUS = TRUE",
            countQuery = "SELECT count(*) from users u where u.EMAIL_VERIFICATION_STATUS = TRUE",
            nativeQuery = true)
    Page<UserEntity> findALlUsersWithConfirmedEmailAddress(Pageable pageableRequest);

    @Query(value = "select * from users u where u.FIRST_NAME = ?1", nativeQuery = true)
    List<UserEntity> findAllUsersByFirstName(String firstName);

    @Query(value = "select * from users u where u.LAST_NAME = :lName", nativeQuery = true)
    List<UserEntity> findAllUsersByLastName(@Param("lName") String lastName);
}