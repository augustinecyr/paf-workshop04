package com.sg.app.pafworkshopd4.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.app.pafworkshopd4.models.PurchaseOrder;

import static com.sg.app.pafworkshopd4.repositories.Queries.*;

@Repository
public class PurchaseOrderRepository {
    @Autowired
    private JdbcTemplate template;

    public boolean insertPurchaseOrder(PurchaseOrder po) {
        return template.update(SQL_INSERT_PURCHASE_ORDER,
                po.getOrderId(), po.getName()) > 0;
    }

}
