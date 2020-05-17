package guru.springframework.springmsbeerservice.services.order;

import guru.springframework.springmsbeercommon.web.config.Constants;
import guru.springframework.springmsbeercommon.web.events.ValidateBeerOrderRequest;
import guru.springframework.springmsbeercommon.web.events.ValidateBeerOrderResponse;
import guru.springframework.springmsbeercommon.web.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ValidateBeerOrderRequestListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator validator;

    @JmsListener(destination = Constants.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateBeerOrderRequest request) {
        boolean isValid = validator.validate(request.getBeerOrderDto());
        jmsTemplate.convertAndSend(Constants.VALIDATE_ORDER_RESULT_QUEUE, ValidateBeerOrderResponse
                .builder()
                .orderId(request.getBeerOrderDto().getId())
                .isValid(isValid)
                .build());
    }
}
