package com.sg.app.pafworkshopd4.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.app.pafworkshopd4.exception.OrderException;
import com.sg.app.pafworkshopd4.models.PurchaseOrder;
import com.sg.app.pafworkshopd4.repositories.LineItemRepository;
import com.sg.app.pafworkshopd4.repositories.PurchaseOrderRepository;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(PurchaseOrder po) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf(">>>> order_id: %s\n", orderId);

        po.setOrderId(orderId);

        // Create the purchaseOrder
        poRepo.insertPurchaseOrder(po);
        System.out.printf(">>>> Order quantity: %s\n", po.getLineItems().size());
        // optional condition
        if (po.getLineItems().size() > 5)
            throw new OrderException("Cannot order more than 5 items");
        // Create the associated line items
        liRepo.addLineItems(po.getLineItems(), orderId);

    }
}