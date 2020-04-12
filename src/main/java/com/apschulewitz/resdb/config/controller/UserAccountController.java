package com.apschulewitz.resdb.config.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dto.UserAccountDto;
import com.apschulewitz.resdb.security.model.dto.UserAccountProjection;
import com.apschulewitz.resdb.security.model.dto.UserAccountWithoutAssociationsDto;
import com.apschulewitz.resdb.security.model.dto.UserDto;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.mapper.UserAccountMapper;
//import com.apschulewitz.resdb.security.model.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token"})
@Slf4j
@Api(value = "User account controller")
public class UserAccountController extends AbstractController<UserAccount, Long> {

    private UserAccountDao userAccountDao;

    private UserAccountMapper userAccountMapper;

    public UserAccountController(UserAccountDao userAccountDao, UserAccountMapper userAccountMapper) {
        this.userAccountDao = userAccountDao;
        this.userAccountMapper = userAccountMapper;
    }

    @ApiOperation(value = "Find all user accounts", notes = "Finds all user accounts", response = List.class)
    @RequestMapping(value = RestUrlPaths.USER_ACCOUNT_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAccountDto>> findAll() {
        Iterable<UserAccount> allUsers = userAccountDao.findAll();
        List<UserAccount> userAccounts = StreamSupport.stream(allUsers.spliterator(), false).collect(Collectors.toList());

        List<UserAccountDto> users = userAccounts.stream()
                .map(userAccountMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
