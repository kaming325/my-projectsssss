package com.example.money_change_backend.order;

import com.example.money_change_backend.MoneyChangeBackendApplication;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    List<Order> findAllByUsernameAndDate(String name, String date);
}
