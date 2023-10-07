package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    @Query("SELECT MAX(u.id) FROM User u")
    Long findMaxId();

    @EntityGraph( type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"pictures"})
    User getWithPicturesByUsername(String username);
}
