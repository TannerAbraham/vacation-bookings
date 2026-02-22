package com.vacation.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "VACATIONS")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vacation_ID")
    private Long vacationId;

    @Column(name = "Vacation_Title", length = 255)
    private String vacationTitle;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "Travel_Fare_Price")
    private Double travelFarePrice;

    @Column(name = "Image_URL", length = 255)
    private String imageUrl;

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Excursion> excursions;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    public Vacation() {}

    public Long getVacationId() { return vacationId; }
    public void setVacationId(Long vacationId) { this.vacationId = vacationId; }

    public String getVacationTitle() { return vacationTitle; }
    public void setVacationTitle(String vacationTitle) { this.vacationTitle = vacationTitle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getTravelFarePrice() { return travelFarePrice; }
    public void setTravelFarePrice(Double travelFarePrice) { this.travelFarePrice = travelFarePrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public List<Excursion> getExcursions() { return excursions; }
    public void setExcursions(List<Excursion> excursions) { this.excursions = excursions; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}
