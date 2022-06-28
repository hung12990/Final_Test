package vn.fis.finaltest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.OrderItemNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.OrderNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.ProductNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.otherexception.*;
import vn.fis.finaltest.dto.AddOrderItemDTO;
import vn.fis.finaltest.dto.CreateOrderDTO;
import vn.fis.finaltest.dto.OrderDTO;
import vn.fis.finaltest.dto.RemoveItemDTO;
import vn.fis.finaltest.model.*;
import vn.fis.finaltest.repository.CustomerRepository;
import vn.fis.finaltest.repository.OrderItemRepository;
import vn.fis.finaltest.repository.OrderRepository;
import vn.fis.finaltest.repository.ProductRepository;
import vn.fis.finaltest.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    private OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
                            CustomerRepository customerRepository,  OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Request All Order. PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepository.findAll(pageable).map(OrderDTO.Mapper::fromEntity);
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return OrderDTO.Mapper.fromEntity(orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException(orderId);
        }));
    }

    @Override
    public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
        Customer customer = customerRepository.findById(createOrderDTO.getCustomerId()).orElseThrow(() -> {
            throw new CustomerNotFoundException(createOrderDTO.getCustomerId());
        });

        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .orderItems(new ArrayList<>())
                .status(Status.CREATED)
                .customer(customer)
                .build();

        createOrderDTO.getOrderItems().stream().forEach(orderItemDTO -> {
            Product product = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(() -> {
                throw new ProductNotFoundException(orderItemDTO.getProductId());
            });

            if(orderItemDTO.getQuantity() > product.getAvaiable()) {
                throw new ProductQuantityNotEnoughException();
            }

            product.setAvaiable(product.getAvaiable() - orderItemDTO.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .quantity(orderItemDTO.getQuantity())
                    .product(product)
                    .order(order)
                    .amount(product.getPrice() * orderItemDTO.getQuantity())
                    .build();

            order.getOrderItems().add(orderItem);
        });

        order.setTotalAmount(order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getAmount()).sum());
        orderRepository.save(order);
        return OrderDTO.Mapper.fromEntity(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException(orderId);
        });
        if(order.getStatus().name().equals("PAID"))
            throw new RemoveOrderStatusException(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO addOrderItem(AddOrderItemDTO addOrderItemDTO) {
        Order order = orderRepository.findById(addOrderItemDTO.getOrderId()).orElseThrow(() -> {
            throw new OrderNotFoundException(addOrderItemDTO.getOrderId());
        });

        if(!order.getStatus().name().equals("CREATED")) {
            throw new AddOrderItemException(order.getStatus());
        }

        Product product = productRepository.findById(addOrderItemDTO.getProductId()).orElseThrow(() -> {
            throw new ProductNotFoundException(addOrderItemDTO.getProductId());
        });

        if(addOrderItemDTO.getQuantity() > product.getAvaiable()) {
            throw new ProductQuantityNotEnoughException();
        }

        product.setAvaiable(product.getAvaiable() - addOrderItemDTO.getQuantity());
        productRepository.save(product);

        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .quantity(addOrderItemDTO.getQuantity())
                .amount(product.getPrice() * addOrderItemDTO.getQuantity())
                .order(order)
                .build();

        order.getOrderItems().add(orderItem);
        order.setTotalAmount(order.getOrderItems().stream().mapToDouble(item -> item.getAmount()).sum());
        orderRepository.save(order);
        return OrderDTO.Mapper.fromEntity(order);
    }

    @Override
    public OrderDTO removeOrderItem(RemoveItemDTO removeItemDTO) {
        Order order = orderRepository.findById(removeItemDTO.getOrderId()).orElseThrow(() -> {
            throw new OrderNotFoundException(removeItemDTO.getOrderId());
        });


        if(!order.getStatus().name().equals("CREATED")) {
            throw new RemoveOrderItemException(Status.CREATED);
        }

        order.getOrderItems().stream().forEach(orderItem -> {
            if(orderItem.getId() == removeItemDTO.getOrderItemId()) {
                Product product = productRepository.findById(orderItem.getProduct().getId()).get();
                product.setAvaiable(product.getAvaiable() + orderItem.getQuantity());
                productRepository.save(product);
            }
        });

        order.getOrderItems().removeIf(orderItem -> orderItem.getId() == removeItemDTO.getOrderItemId());
        order.setTotalAmount(order.getOrderItems().stream().mapToDouble(item -> item.getAmount()).sum());
        OrderItem o = orderItemRepository.findById(removeItemDTO.getOrderItemId()).orElseThrow(
                () -> {
                    throw new OrderItemNotFoundException(removeItemDTO.getOrderItemId());
                }
        );
        orderItemRepository.deleteById(removeItemDTO.getOrderItemId());

        orderRepository.save(order);

        return OrderDTO.Mapper.fromEntity(order);
    }

    @Override
    public OrderDTO convertCreatedToPaid(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException(orderId);
        });

        if(!order.getStatus().name().equals("CREATED")) {
            throw new ConvertSatusCreatedToPaidException(order.getStatus());
        }
        order.setStatus(Status.PAID);
        orderRepository.save(order);
        return OrderDTO.Mapper.fromEntity(order);
    }

    @Override
    public OrderDTO convertCreatedToCancelled(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException(orderId);
        });
        if(!order.getStatus().name().equals("CREATED")) {
            throw new ConvertSatusCreatedToCancelledException(order.getStatus());
        }
        order.setStatus(Status.CANCELLED);
        orderRepository.save(order);
        return OrderDTO.Mapper.fromEntity(order);
    }
}
