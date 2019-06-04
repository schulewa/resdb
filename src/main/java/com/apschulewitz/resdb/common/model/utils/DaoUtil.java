package com.apschulewitz.resdb.common.model.utils;

import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 29/04/2017.
 */
@Component
public class DaoUtil<T> {

    @Autowired
    private UserAccountDao userAccountDao;

    public List<T> iterToList(Iterable<T> iter) {
        List<T> data = new ArrayList<>();
        if (iter == null) {
            return data;
        }

        StreamSupport.stream(iter.spliterator(), false).forEach(e -> data.add(e));
        return data;
    }

    public UserAccount lookupAdminUserAccount() {
        return userAccountDao.findByLogonName("adrian");
    }
}
