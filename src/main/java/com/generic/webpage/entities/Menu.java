package com.generic.webpage.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@NoArgsConstructor
public class Menu implements IConvertible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "label", columnDefinition = "varchar(255)")
    private String label;

    @Getter
    @Setter
    @Column(name = "icon", columnDefinition = "varchar(63)")
    private String icon;

    @Getter
    @Setter
    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Getter
    @Setter
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean visible;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
    private Page page;

}