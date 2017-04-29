package me.kamil.simpleshop.domain.repository;

import me.kamil.simpleshop.auth.domain.User;
import me.kamil.simpleshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kamil on 2017-04-15.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByFirstName(String firstName);

    List<Order> findByLastName(String lastName);

    List<Order> findByPostalCode(String postalCode);

    List<Order> findByPhoneNumber(String phoneNumber);

    List<Order> findByStatus(String status);
}
