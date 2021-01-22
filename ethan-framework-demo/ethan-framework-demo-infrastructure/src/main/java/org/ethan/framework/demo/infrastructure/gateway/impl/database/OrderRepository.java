package org.ethan.framework.demo.infrastructure.gateway.impl.database;

import org.ethan.framework.demo.infrastructure.gateway.impl.database.po.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, Long> {
}
