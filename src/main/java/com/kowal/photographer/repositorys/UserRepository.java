package com.kowal.photographer.repositorys;

import com.kowal.photographer.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph( type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"pictures"})
    User findUserByUsername(String username);
    List<User> findAllByRole(String role);
}
