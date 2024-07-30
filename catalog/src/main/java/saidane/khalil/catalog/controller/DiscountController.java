package saidane.khalil.catalog.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import saidane.khalil.catalog.domain.request.DiscountRequest;
import saidane.khalil.catalog.domain.response.DiscountResponse;
import saidane.khalil.catalog.service.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
@Api("Discount")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    public DiscountResponse saveDiscount(@RequestBody DiscountRequest request) {
        return discountService.saveDiscount(request);
    }

    @GetMapping
    public List<DiscountResponse> findAllDiscounts() {
        return discountService.findAllDiscount();
    }
}
