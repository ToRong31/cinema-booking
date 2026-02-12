package com.cinema.payment.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long bookingId;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    
    @Column(unique = true)
    private String transactionId;
    
    private String paymentGatewayResponse;
    
    private LocalDateTime paymentTime = LocalDateTime.now();
    
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING, WALLET
    }
    
    public enum Status {
        PENDING, SUCCESS, FAILED, REFUNDED
    }
    
    public Payment() {}
    
    public Payment(Long id, Long bookingId, Long userId, Double amount, PaymentMethod paymentMethod,
                   Status status, String transactionId, String paymentGatewayResponse, LocalDateTime paymentTime) {
        this.id = id;
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionId = transactionId;
        this.paymentGatewayResponse = paymentGatewayResponse;
        this.paymentTime = paymentTime;
    }
    
    public Long getId() { return id; }
    public Long getBookingId() { return bookingId; }
    public Long getUserId() { return userId; }
    public Double getAmount() { return amount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public Status getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
    public String getPaymentGatewayResponse() { return paymentGatewayResponse; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    
    public void setId(Long id) { this.id = id; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(Status status) { this.status = status; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setPaymentGatewayResponse(String paymentGatewayResponse) { this.paymentGatewayResponse = paymentGatewayResponse; }
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; }
}
