package com.vacation.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_ID")
    private Long countryId;

    @Column(name = "Country", length = 255)
    private String country;

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Division> divisions;

    public Country() {}

    public Long getCountryId() { return countryId; }
    public void setCountryId(Long countryId) { this.countryId = countryId; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public List<Division> getDivisions() { return divisions; }
    public void setDivisions(List<Division> divisions) { this.divisions = divisions; }
}
