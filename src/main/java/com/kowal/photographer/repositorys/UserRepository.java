package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph( type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"pictures"})
    User findUserByUsername(String username);
    List<User> findAllByRole(String role);

    @EntityGraph( type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"pictures"})
    User getWithPicturesByUsername(String username);
}
