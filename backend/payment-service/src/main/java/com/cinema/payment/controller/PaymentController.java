package com.cinema.payment.controller;

import com.cinema.payment.dto.PaymentRequest;
import com.cinema.payment.entity.Payment;
import com.cinema.payment.service.PaymentService;
import com.cinema.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Payment>>> getAllPayments(
            @RequestParam(required = false) Long userId) {
        List<Payment> payments = userId != null 
            ? paymentService.getPaymentsByUserId(userId)
            : paymentService.getAllPayments();
        return ResponseEntity.ok(ApiResponse.success(payments));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success(payment));
    }
    
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<ApiResponse<Payment>> getPaymentByTransactionId(@PathVariable String transactionId) {
        Payment payment = paymentService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(ApiResponse.success(payment));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Payment>> processPayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(request);
        
        if (payment.getStatus() == Payment.Status.SUCCESS) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Payment processed successfully", payment));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Payment processing failed"));
        }
    }
    
    @PutMapping("/{id}/refund")
    public ResponseEntity<ApiResponse<Payment>> refundPayment(@PathVariable Long id) {
        Payment payment = paymentService.refundPayment(id);
        return ResponseEntity.ok(ApiResponse.success("Payment refunded successfully", payment));
    }
}
