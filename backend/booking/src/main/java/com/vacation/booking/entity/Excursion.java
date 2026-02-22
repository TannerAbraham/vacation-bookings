package com.vacation.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EXCURSIONS")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Excursion_ID")
    private Long excursionId;

    @Column(name = "Excursion_Title", length = 255)
    private String excursionTitle;

    @Column(name = "Excursion_Price")
    private Double excursionPrice;

    @Column(name = "Image_URL", length = 255)
    private String imageUrl;

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions", fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    public Excursion() {}

    public Long getExcursionId() { return excursionId; }
    public void setExcursionId(Long excursionId) { this.excursionId = excursionId; }

    public String getExcursionTitle() { return excursionTitle; }
    public void setExcursionTitle(String excursionTitle) { this.excursionTitle = excursionTitle; }

    public Double getExcursionPrice() { return excursionPrice; }
    public void setExcursionPrice(Double excursionPrice) { this.excursionPrice = excursionPrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public Vacation getVacation() { return vacation; }
    public void setVacation(Vacation vacation) { this.vacation = vacation; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}
