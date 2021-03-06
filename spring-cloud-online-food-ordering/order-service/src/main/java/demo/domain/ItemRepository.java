package demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByRestaurantId(String restaurantId);
    List<Item> findByOrderInfo(OrderInfo orderInfo);
}
