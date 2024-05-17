package br.com.stoom.store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_categories")
public class Category {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.DETACH)
    private List<Product> products;

}
