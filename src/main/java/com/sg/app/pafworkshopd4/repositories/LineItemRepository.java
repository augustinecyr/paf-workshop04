package com.sg.app.pafworkshopd4.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.app.pafworkshopd4.models.LineItem;
import com.sg.app.pafworkshopd4.models.PurchaseOrder;

import static com.sg.app.pafworkshopd4.repositories.Queries.*;

@Repository
public class LineItemRepository {
    @Autowired
    private JdbcTemplate template;

    public void addLineItems(PurchaseOrder purchaseOrder) {
        addLineItems(purchaseOrder.getLineItems(), purchaseOrder.getOrderId());
    }

    public void addLineItems(List<LineItem> lineItems, String orderId) {
        List<Object[]> data = lineItems.stream()
                .map(li -> {
                    Object[] l = new Object[3];
                    l[0] = li.getDescription();
                    l[1] = li.getQuantity();
                    l[2] = orderId;
                    return l;
                })
                .toList();

        // Batch update
        template.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }
}