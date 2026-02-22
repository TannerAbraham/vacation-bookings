package com.vacation.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "division")
@Getter
@Setter
@NoArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "division_name")
    private String divisionName;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
}
