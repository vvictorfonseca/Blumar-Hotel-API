package com.projeto.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.hotel.model.ClientPurchase;


@Repository
public interface ClientPurchaseRepository extends CrudRepository<ClientPurchase, Long> {
  
  @Query(value = "SELECT * FROM client_purchases WHERE codigo_check_in = :codigo", nativeQuery = true)
  List<ClientPurchase> clientPurchases(Long codigo);

}
