package org.ethan.framework.batch.mongodb.repository;

import org.ethan.framework.batch.mongodb.document.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface OrderRepository extends PagingAndSortingRepository<Order, BigInteger> {
}
