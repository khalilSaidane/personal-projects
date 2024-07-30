package saidane.khalil.prospect.domain.request;

public record AddressRequest (
        String street,
        Long streetNumber,
        String zipCode,
        String city,
        String country
){
}
