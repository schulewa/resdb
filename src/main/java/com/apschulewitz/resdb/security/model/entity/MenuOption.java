package com.apschulewitz.resdb.security.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.MaintainableDataObject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

import static com.apschulewitz.resdb.common.model.entity.DataOperation.VALID_DATA_OPERATIONS;
import static com.apschulewitz.resdb.common.model.entity.MaintainableDataObject.VALID_MAINTAINABLE_DATA_OBJECT;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_menu_option")
public class MenuOption {
	private static final long serialVersionUID = -3806336965863646725L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_object", nullable = false, length = 50)
    private MaintainableDataObject dataObject;

    @Column(nullable = false)
    private DataOperation operation;

    @Column(name = "default_menu_text", nullable = false, length = 50)
    private String defaultMenuText;


    public boolean isValidOperation(DataOperation op)
    {
        return VALID_DATA_OPERATIONS.contains(op);
    }

    public boolean isValidDataObject(MaintainableDataObject obj)
    {
        return VALID_MAINTAINABLE_DATA_OBJECT.contains(obj);
    }

    @Tolerate
    public MenuOption() {

    }
}
