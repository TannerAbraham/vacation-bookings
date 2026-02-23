package com.vacation.booking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "divisions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;

    // Field renamed to match what Angular expects ("division_name")
    // @Column maps it to the existing "division" column in the DB
    @Column(name = "division")
    private String division_name;

    // Exposes country_id as a plain serialized field alongside the @ManyToOne
    // insertable/updatable = false so JPA doesn't try to write it twice
    @Column(name = "country_id", insertable = false, updatable = false)
    private Long country_id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> customers;
}