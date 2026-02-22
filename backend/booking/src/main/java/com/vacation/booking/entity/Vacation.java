package com.vacation.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vacation")
@Getter
@Setter
@NoArgsConstructor
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacation_title")
    private String vacationTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "travel_price")
    private BigDecimal travelPrice;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Excursion> excursions;
}
