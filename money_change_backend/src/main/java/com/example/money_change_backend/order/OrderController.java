package com.example.money_change_backend.order;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moneyChange/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/getAll")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/insert")
    public String saveOrder(@RequestParam String name,
                            @RequestParam int totalAmount,
                            @RequestParam int payment,
                            @RequestParam int changeAmount,
                            @RequestParam String date
                            ){ // @RequestBody Order order
        ObjectId _id = new ObjectId();
        Order order = new Order(_id, name, totalAmount, payment, changeAmount, date);
        orderRepository.save(order);
        return "Order added with id: " + order.get_id();
    }

    @GetMapping("/getOrder/{id}")
    public Optional<Order> getOrder(@PathVariable ObjectId id){
        return orderRepository.findById(id);
    }

    @GetMapping("/getOrder")
    public List<Order> getOrderByDate(@RequestParam String name,
                                      @RequestParam String date){
        return orderRepository.findAllByUsernameAndDate(name, date);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable ObjectId id){
        orderRepository.deleteById(id);
        return "order deleted with id: " + id;
    }
}
