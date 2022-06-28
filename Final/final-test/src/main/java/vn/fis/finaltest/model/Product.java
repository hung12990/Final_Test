package vn.fis.finaltest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 10, max = 100)
    private String name;

    @Column(name = "price")
    @NotNull
    @Min(1)
    private Double price;

    @Column(name = "avaiable")
    @NotNull
    @Min(value = 0)
    private Long avaiable;
}
