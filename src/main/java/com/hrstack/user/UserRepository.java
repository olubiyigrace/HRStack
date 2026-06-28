package com.hrstack.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByWorkspaceUrl(String workspaceUrl);
    Optional<User> findByEmail(String email);
}
