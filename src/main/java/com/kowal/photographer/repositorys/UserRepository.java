package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    @Query("SELECT MAX(u.id) FROM User u")
    Long findMaxId();
}
