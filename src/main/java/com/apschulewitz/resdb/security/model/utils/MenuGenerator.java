package com.apschulewitz.resdb.security.model.utils;

import com.apschulewitz.resdb.security.model.entity.Menu;

/**
 * Created by adrianschulewitz on 20/08/2016.
 */
public final class MenuGenerator {

   private static final Menu.MenuBuilder builder = Menu.builder();

    public static Menu generate() {
        Menu defaultMenu = Menu.builder().build();

        return defaultMenu;
    }

}
