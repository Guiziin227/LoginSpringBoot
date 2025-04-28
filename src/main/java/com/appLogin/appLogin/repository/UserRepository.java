package com.appLogin.appLogin.repository;

import com.appLogin.appLogin.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
