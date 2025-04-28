package com.appLogin.appLogin.repository;

import com.appLogin.appLogin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {

    User findById(int id);

    @Query(value = "SELECT * FROM applogin.user WHERE email = :email and password = :password", nativeQuery = true)

    public User Login(String email, String password);
}
