package com.aize.assignment.repos;

import com.aize.assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
