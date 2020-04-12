package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;


/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_menu_item")
public class MenuItem {
	private static final long serialVersionUID = -8748917804636474159L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne //(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu owningMenu;

	@Column(name = "menu_level", nullable = false)
    private Integer menuLevel;         // menuLevel within the menu; 1=top-menuLevel, >1=sub-menu

	@Column(name = "sequence_no", nullable = false)
    private Integer sequenceNumber;    //sequence within the menu

//	@Column(name = "internal_menuoption_id", nullable = false)
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id", unique = true, nullable = false, updatable = false)
    private MenuOption internalMenuOption;
//    private Integer internalMenuOptionId;

//	@ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "menu_id")
//    private Menu  parentMenu;

	@Column(name = "item_text", nullable = false, length = 30)
    private String text;            // the text that appears on the menu

	@Column(nullable = true, length = 50)
    private String prompt;          // the prompt that appears when the mouse hovers over the menu text

	@Column(name = "is_leaf_node", nullable = false)
    private boolean leafNode;     // are there any further sub-menus hanging off this item

	@Column(name = "has_separator_before", nullable = true)
    private boolean separatorBefore;

	@Column(name = "has_separator_after", nullable = true)
    private boolean separatorAfter;

    //private boolean hasCreatePermission = false;
    //private boolean hasWritePermission = false;
    //private boolean hasDeletePermission = false;

    @Tolerate
    public MenuItem() {

    }
}
