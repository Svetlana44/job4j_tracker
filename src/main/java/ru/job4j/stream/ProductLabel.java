package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .flatMap(Stream::ofNullable)
                .filter(product -> product.getStandard() - product.getActual() >= 0 && product.getStandard() - product.getActual() <= 3)
                .map(p -> new Label(p.getName(), p.getPrice() / 2).toString())
                .collect(Collectors.toList());
    }
}