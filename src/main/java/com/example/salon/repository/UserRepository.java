package com.example.salon.repository;

import com.example.salon.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("select u from User u left join fetch u.employee where u.login = :login")
    User findUserByLogin(@Param("login") String login);
}
