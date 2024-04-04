package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {


        // Retrieve reservation by reservationId
        Optional<Reservation> optionalReservation = reservationRepository2.findById(reservationId);
        if (!optionalReservation.isPresent()) {
            throw new Exception("Reservation not found for ID: " + reservationId);
        }
        Reservation reservation = optionalReservation.get();

        // Validate payment amount
        int billAmount = reservation.getNumberOfHours() * reservation.getSpot().getPricePerHour();
        if (amountSent < billAmount) {
            throw new Exception("Insufficient Amount");
        }

        // Validate payment mode
        PaymentMode paymentMode;
        switch (mode.toLowerCase()) {
            case "cash":
                paymentMode = PaymentMode.CASH;
                break;
            case "card":
                paymentMode = PaymentMode.CARD;
                break;
            case "upi":
                paymentMode = PaymentMode.UPI;
                break;
            default:
                throw new Exception("Payment mode not detected");
        }

        // Create payment entity
        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setPaymentMode(paymentMode);

        // Save payment entity
         paymentRepository2.save(payment);
         return payment;
    }
//    private int calculateBillAmount(Reservation reservation) {
//        // Your logic to calculate bill amount based on reservation details
//        // For example, if you have the price per hour and duration of reservation, you can calculate bill as pricePerHour * numberOfHours
//        return reservation.getNumberOfHours() * reservation.getNumberOfHours();
//    }


}
