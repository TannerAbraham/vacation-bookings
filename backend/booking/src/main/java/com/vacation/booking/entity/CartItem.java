package com.vacation.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CART_ITEMS")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_Item_ID")
    private Long cartItemId;

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Last_Update")
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "excursion_cartitem",
        joinColumns = @JoinColumn(name = "Cart_Item_ID"),
        inverseJoinColumns = @JoinColumn(name = "Excursion_ID")
    )
    private List<Excursion> excursions;

    public CartItem() {}

    public Long getCartItemId() { return cartItemId; }
    public void setCartItemId(Long cartItemId) { this.cartItemId = cartItemId; }

    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }

    public Vacation getVacation() { return vacation; }
    public void setVacation(Vacation vacation) { this.vacation = vacation; }

    public List<Excursion> getExcursions() { return excursions; }
    public void setExcursions(List<Excursion> excursions) { this.excursions = excursions; }
}
