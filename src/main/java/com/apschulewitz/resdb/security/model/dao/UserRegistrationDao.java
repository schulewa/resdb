package com.apschulewitz.resdb.security.model.dao;

import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
public interface UserRegistrationDao extends CrudRepository<UserRegistration, Long> {

  Optional<UserRegistration> findByEmail(String email);

  Optional<UserRegistration> findByVerificationCode(String code);

}
