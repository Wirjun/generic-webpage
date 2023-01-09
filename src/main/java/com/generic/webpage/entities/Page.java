package com.generic.webpage.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "page")
@NoArgsConstructor
public class Page implements IConvertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "title", columnDefinition = "varchar(255)")
    private String title;

    @Getter
    @Setter
    @Column(name = "content", columnDefinition = "varchar(255)")
    private String content;

    @Getter
    @Setter
    @Column(name = "alias", columnDefinition = "varchar(255)")
    private String alias;

    @Getter
    @Setter
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean visible;

    @Getter
    @Setter
    @Column(name = "deletable", columnDefinition = "boolean default false", nullable = false)
    private boolean deletable;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_fk", referencedColumnName = "id")
    private Menu menu;
}