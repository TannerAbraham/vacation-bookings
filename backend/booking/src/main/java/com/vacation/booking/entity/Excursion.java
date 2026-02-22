package com.vacation.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "excursion")
@Getter
@Setter
@NoArgsConstructor
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "excursion_title")
    private String excursionTitle;

    @Column(name = "excursion_price")
    private BigDecimal excursionPrice;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions", fetch = FetchType.LAZY)
    private Set<CartItem> cartItems;
}
