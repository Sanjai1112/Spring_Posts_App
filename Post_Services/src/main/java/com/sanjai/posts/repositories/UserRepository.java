package com.sanjai.posts.repositories;

import com.sanjai.posts.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Long> {
    @Query(value = "SELECT * FROM USER_DETAILS WHERE COMPANY=?1", nativeQuery = true)
    List<UserDetails> findAllUsersByCompanyName(String companyName);
}
