package br.com.stoom.store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_brands")
public class Brand {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.DETACH)
    private List<Product> products;

}
