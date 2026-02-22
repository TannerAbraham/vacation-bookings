package com.vacation.booking.dao;

import com.vacation.booking.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "cart-items", path = "cart-items")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
