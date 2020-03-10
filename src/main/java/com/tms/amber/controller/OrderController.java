package com.tms.amber.controller;

import com.tms.amber.domain.Order;
import com.tms.amber.service.OrderService;
import com.tms.amber.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class OrderController {
    @Autowired
    private MessageSource messageSource;

    private OrderService service = new OrderServiceImpl();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showOrders(Locale locale, Model model) {
        List<Order> orders = service.getAllOrders();
        model.addAttribute("orders", orders);
        return "allOrders";
    }

    //get - страница добавления
    @RequestMapping(value = "/add-order", method = RequestMethod.GET)
    public String addOrderPage(Locale locale, Model model) {
        return "/addOrder";
    }

    //post - сохранение
    //@Valid - аннотация для валидирования параметров
    //401  - BadRequest
    //@BandingError
    //лучше String price для обработки
    @RequestMapping(value = "/add-order", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String addOrder(@RequestParam(value = "title") String title, @RequestParam(value = "price") String price) {
        service.addOrder(title, Double.valueOf(price));
        return "redirect:/";
        // return "redirect:/"; - остаться на той же странице
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateOrderPage(@PathVariable Long id, Model model, Locale locale) {
        Order order = service.getById(id).get();
        model.addAttribute(order);
        return "updateOrder";
    }

    @RequestMapping(value = "/update-order/{id}", method = RequestMethod.POST)
    public String updateOrder(@PathVariable Long id, @RequestParam(value = "title") String name, @RequestParam(value = "price") String price) {
        Double priceDouble = Double.valueOf(price);
        Order order = service.getById(id).get();
        if (!order.getName().equals(name)) {
            order.setName(name);
        }
        if (!order.getPrice().equals(priceDouble)) {
            order.setPrice(priceDouble);
        }
        service.updateOrder(order);

        return "redirect:/";
    }
}
