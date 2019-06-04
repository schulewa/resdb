/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.security.model.entity;


import com.apschulewitz.resdb.common.model.entity.MenuOwnerType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name="resdb_menu")
public class Menu {
	private static final long serialVersionUID = 6333207175587279945L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_type", nullable = false)
    private MenuOwnerType ownerType;

    @OneToMany(mappedBy = "owningMenu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MenuItem> menuItems;

    @Tolerate
    public Menu() {

    }
}
