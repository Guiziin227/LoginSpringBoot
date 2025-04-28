package com.appLogin.appLogin.repository;

import com.appLogin.appLogin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {

    User findById(int id);

    @Query(value = "SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1", nativeQuery = true)
    User Login(@Param("email") String email, @Param("password") String password);
}
