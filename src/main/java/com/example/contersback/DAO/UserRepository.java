package com.example.contersback.DAO;

import com.example.contersback.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Operator, Long> {
    Operator getUserByLogin(String login);
    Operator findByLogin(String login);
}
