package csd230.lab1.controllers;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.entities.CartEntity;
import csd230.lab1.entities.OrderEntity;
import csd230.lab1.entities.ProductEntity;
import csd230.lab1.entities.PublicationEntity;
import csd230.lab1.repositories.BookEntityRepository;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.OrderEntityRepository;
import csd230.lab1.repositories.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartEntityRepository cartRepository;
    @Autowired
    private BookEntityRepository bookRepository;
    @Autowired
    private OrderEntityRepository orderRepository;
    @Autowired
    private ProductEntityRepository productRepository;

    @GetMapping
    public String viewCart(Model model) {
        Long defaultCartId = 1L;

        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setId(defaultCartId);
                    return cartRepository.save(newCart);
                });
        model.addAttribute("cart", cart);
        return "cartDetails";
    }

    @GetMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if (cart != null && book != null) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }
        return "redirect:/books";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if(cart != null && book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);

        if (cart == null || cart.getProducts().isEmpty()) {
            return "redirect:/cart";
        }

        // Create the Order
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0.0;
        List<ProductEntity> productsToUpdate = new ArrayList<>();

        for (ProductEntity product : cart.getProducts()) {
            totalAmount += product.getPrice();

            if (product instanceof PublicationEntity) {
                PublicationEntity pub = (PublicationEntity) product;
                if (pub.getCopies() > 0) {
                    pub.setCopies(pub.getCopies() - 1);
                    productsToUpdate.add(pub);
                }
            }
            order.addProduct(product);
        }

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        productRepository.saveAll(productsToUpdate);
        cart.getProducts().clear();
        cartRepository.save(cart);
        return "redirect:/cart/orderDetails?id=" + order.getId();
    }

    @GetMapping("/orderDetails")
    public String orderDetails(@RequestParam("id") Long orderId, Model model) {
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
        model.addAttribute("order", order);
        return "orderDetails";
    }
}
