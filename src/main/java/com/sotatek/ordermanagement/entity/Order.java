package com.sotatek.ordermanagement.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bill")
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "totalMoney")
    private Double totalMoney;

    @Column(name = "issueDate")
    private Date issueDate;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<LineOrder> lineOrders;
}
