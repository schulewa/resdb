package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Adrian
 * Date: 27/05/13
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
@Entity
@Table(name = "resdb_publication_role")
//@Audited
public class PublicationRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "chapter_title", nullable = false, length = 50)
    private String chapterTitle;

    @Tolerate
    public PublicationRole() {

    }
}
