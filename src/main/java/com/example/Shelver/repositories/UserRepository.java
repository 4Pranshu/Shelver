package com.example.Shelver.repositories;

import com.example.Shelver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {
}
