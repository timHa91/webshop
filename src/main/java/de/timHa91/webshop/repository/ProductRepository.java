package de.timHa91.webshop.repository;

import de.timHa91.webshop.model.ProductCreateRequest;
import de.timHa91.webshop.model.ProductResponse;
import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {

    private List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new ProductResponse(
                UUID.randomUUID().toString(),
                "AMD Ryzen 9 5950X",
                "Bester CPU auf dem Markt",
                79900,
                Arrays.asList("AMD", "Processor")
        ));
        products.add(new ProductResponse(
                UUID.randomUUID().toString(),
                "Intel Core i9-9980F",
                "Zweit bester CPU auf dem Markt",
                37900,
                Arrays.asList("Intel", "Processor")
        ));
        products.add(new ProductResponse(
                UUID.randomUUID().toString(),
                "NVIDIA GeForce GTX 1080 Ti Black Edition 11GB",
                "Bester GPU auf dem Markt",
                74900,
                Arrays.asList("NVIDIA", "graphics")
        ));
    }

    public List<ProductResponse> findAll(String tag) {
        if (tag == null)
            return products;
        else {
            String lowerCaseTag = tag.toLowerCase();

            // filter products by tag
            return products.stream()
                    .filter(p -> lowercaseTags(p).contains(lowerCaseTag))
                    .collect(Collectors.toList());
        }
    }



    private List<String> lowercaseTags (ProductResponse p) {
        return p.getTags().stream()
                .map(tag -> tag.toLowerCase())
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                //Zufällig generierte ID
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(response);
        return response;
    }
}
