package saidane.khalil.prospect.connector.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import saidane.khalil.prospect.connector.cart.model.CartResponse;

@FeignClient(value = "carts", url = "${environment.url.cart}" ,path = "/api/v1")
public interface CartClient {

    @PostMapping("/carts/{prospectId}")
    CartResponse createCart(@PathVariable Long prospectId);

}
