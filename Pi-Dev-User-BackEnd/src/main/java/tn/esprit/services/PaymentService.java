package tn.esprit.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import tn.esprit.dto.PaymentDTO;
import tn.esprit.entities.Payment;
import tn.esprit.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(PaymentDTO paymentDTO) throws StripeException {
        Stripe.apiKey = "sk_test_51IU0QSFtVw2f370Sy8x9yPrGwBNk3ofSxu3Pn9UqRNx5NZDKm2zZRL18UG3pfR6Sky85hsRiP7ojS8nihuf1CLEP00fNw32o4U";

        long amount = Math.round(paymentDTO.getCost()*100);

          // Create Customer

     CustomerCreateParams params = CustomerCreateParams.builder()
     .setEmail(paymentDTO.getUserEmail())
     .setName(paymentDTO.getUserName())
     .setDescription("DariTn Customer").build();
         
 Customer customer = Customer.create(params);

     //Set card details
     Map<String, Object> card = new HashMap<>();
     card.put("number", paymentDTO.getCardNumber());
     card.put("exp_month", paymentDTO.getExpirationMonth());
     card.put("exp_year", paymentDTO.getExpirationYear());
     card.put("cvc", paymentDTO.getCvc());

     Map<String, Object> paramsCard = new HashMap<>();
     paramsCard.put("type", "card");
     paramsCard.put("card", card);

     PaymentMethod paymentMethod = PaymentMethod.create(paramsCard);
    
     Map<String, Object> paramsUpdate = new HashMap<>();
    paramsUpdate.put("customer", customer.getId());

   PaymentMethod updatedPaymentMethod = paymentMethod.attach(paramsUpdate);

    PaymentIntentCreateParams paramsIntent = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency("eur")
            .addPaymentMethodType("card")
            .setPaymentMethod(paymentMethod.getId())
            .setPaymentMethod(updatedPaymentMethod.getId())
            .setCustomer(customer.getId()).build();

    PaymentIntent paymentIntent = PaymentIntent.create(paramsIntent).confirm();

        Payment payment = Payment.builder()
            .refPayment(paymentIntent.getId())
            .cost(paymentDTO.getCost())
            .creationDate(LocalDate.now())
            .userName(paymentDTO.getUserName()).build();

        return paymentRepository.save(payment);
    }

}
