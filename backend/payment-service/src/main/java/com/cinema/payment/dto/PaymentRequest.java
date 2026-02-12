package com.cinema.payment.dto;

import com.cinema.payment.entity.Payment;

public class PaymentRequest {
    private Long bookingId;
    private Long userId;
    private Double amount;
    private Payment.PaymentMethod paymentMethod;
    
    public Long getBookingId() { return bookingId; }
    public Long getUserId() { return userId; }
    public Double getAmount() { return amount; }
    public Payment.PaymentMethod getPaymentMethod() { return paymentMethod; }
}
