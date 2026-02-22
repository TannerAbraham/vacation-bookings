package com.vacation.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_item_excursion",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )
    private Set<Excursion> excursions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
