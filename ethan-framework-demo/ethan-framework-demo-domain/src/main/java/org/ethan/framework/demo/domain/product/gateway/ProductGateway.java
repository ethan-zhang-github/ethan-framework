package org.ethan.framework.demo.domain.product.gateway;

import org.ethan.framework.demo.domain.product.Product;

public interface ProductGateway {

    Product getById(Long id);

}
