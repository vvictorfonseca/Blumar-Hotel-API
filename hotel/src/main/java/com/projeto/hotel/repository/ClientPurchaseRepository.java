package com.projeto.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.ClientPurchase;

@Repository
public interface ClientPurchaseRepository extends CrudRepository<ClientPurchase, Long> {
  
  @Query(value = "SELECT * FROM client_purchases WHERE check_in_id = :id", nativeQuery = true)
  List<ClientPurchase> clientPurchases(Long id);

  @Query(value = "SELECT product_name, product_price FROM client_purchases WHERE check_in_id = :id", nativeQuery = true)
  List<Object> clientProducts(Long id);

}
