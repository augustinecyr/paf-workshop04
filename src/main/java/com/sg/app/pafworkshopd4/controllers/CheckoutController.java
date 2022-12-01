package com.sg.app.pafworkshopd4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.app.pafworkshopd4.exception.OrderException;
import com.sg.app.pafworkshopd4.models.LineItem;
import com.sg.app.pafworkshopd4.models.PurchaseOrder;
import com.sg.app.pafworkshopd4.services.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/checkout")
public class CheckoutController {

    @Autowired
    OrderService orderSvc;

    @PostMapping
    public String postCheckout(Model model, HttpSession sess) throws OrderException {

        List<LineItem> lineItems = (List<LineItem>) sess.getAttribute("cart");
        PurchaseOrder po = (PurchaseOrder) sess.getAttribute("checkoutCart");
        // Destroy the session
        sess.invalidate();
        orderSvc.createNewOrder(po);
        model.addAttribute("total", lineItems.size());

        return "checkout";
    }
}