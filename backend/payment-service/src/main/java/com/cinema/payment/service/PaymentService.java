package com.cinema.payment.service;

import com.cinema.payment.dto.PaymentRequest;
import com.cinema.payment.entity.Payment;
import com.cinema.payment.repository.PaymentRepository;
import com.cinema.common.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
    
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
    }
    
    public Payment getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with transaction id: " + transactionId));
    }
    
    public Payment processPayment(PaymentRequest request) {
        // In a real scenario, you would integrate with payment gateway
        // This is a simplified version
        
        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(generateTransactionId());
        
        // Simulate payment processing
        boolean paymentSuccess = simulatePaymentGateway();
        
        if (paymentSuccess) {
            payment.setStatus(Payment.Status.SUCCESS);
            payment.setPaymentGatewayResponse("Payment processed successfully");
        } else {
            payment.setStatus(Payment.Status.FAILED);
            payment.setPaymentGatewayResponse("Payment failed");
        }
        
        return paymentRepository.save(payment);
    }
    
    public Payment refundPayment(Long id) {
        Payment payment = getPaymentById(id);
        
        if (payment.getStatus() != Payment.Status.SUCCESS) {
            throw new RuntimeException("Only successful payments can be refunded");
        }
        
        payment.setStatus(Payment.Status.REFUNDED);
        payment.setPaymentGatewayResponse("Payment refunded successfully");
        
        return paymentRepository.save(payment);
    }
    
    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
    }
    
    private boolean simulatePaymentGateway() {
        // Simulate 95% success rate
        return Math.random() < 0.95;
    }
}
