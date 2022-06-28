package vn.fis.finaltest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_customer")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 10, max = 100)
    private String name;

    @Column(name = "mobile", unique = true)
    @NotNull
    private String mobile;

    @Column(name = "address")
    @NotNull
    @Size(min = 10, max = 100)
    private String address;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> orders;
}
