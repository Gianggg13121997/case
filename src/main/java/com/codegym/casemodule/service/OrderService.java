package com.codegym.casemodule.service;

import com.codegym.casemodule.repo.IOrderRepository;
import com.codegym.casemodule.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    public Iterable<Order> findAll() {
        return iOrderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return iOrderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        iOrderRepository.save(order);

    }

    @Override
    public void remove(Long id) {
        iOrderRepository.deleteById(id);

    }
}
