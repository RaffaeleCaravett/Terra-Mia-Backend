package com.example.TerraMia.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE YEAR(o.createdAt) = :year")
    List<Order> findOrdersByYear(@Param("year") int year);

    @Query("SELECT o FROM Order o WHERE YEAR(o.createdAt) = :year AND MONTH(o.createdAt) = :month")
    List<Order> findOrdersByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT o FROM Order o WHERE YEAR(o.createdAt) = :year AND MONTH(o.createdAt) = :month AND DAY(o.createdAt) = :day")
    List<Order> findOrdersByDate(@Param("year") int year, @Param("month") int month, @Param("day") int day);
}
