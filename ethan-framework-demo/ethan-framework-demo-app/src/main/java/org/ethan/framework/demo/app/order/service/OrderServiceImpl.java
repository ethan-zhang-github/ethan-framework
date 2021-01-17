package org.ethan.framework.demo.app.order.service;

import lombok.extern.slf4j.Slf4j;
import org.ethan.framework.demo.client.api.OrderService;
import org.ethan.framework.demo.client.dto.CreateOrderCmd;
import org.ethan.framework.demo.domain.product.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /*@Resource
    private ExtensionExecutor extensionExecutor;*/

    @Override
    public void createOrder(CreateOrderCmd createOrderCmd) {
        // ... do something
        /*Product product = extensionExecutor.execute(Scene.of("order", "createOrder", createOrderCmd.getClient()), ProductService.class,
                (ProductService productService) -> productService.getProduct(createOrderCmd.getProductId()));
        log.info("get product : {}", JSON.toJSONString(product));*/
        // ... do something
    }

}
