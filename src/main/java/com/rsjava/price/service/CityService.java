package com.rsjava.price.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class CityService {

    private String link;

    public CityService(String link) {
        this.link = link;
    }

    private String numberOfElements() {
        Elements elements2 = null;
        try {
            Document document = Jsoup.connect(link).get();
            elements2 = document.getElementsByClass("offers-index pull-left text-nowrap");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements2.get(0).text();
    }

    private int numberOfElementsInteger() {
        String number = numberOfElements().replace(" ", "");
        String number2 = number.substring(number.indexOf(":") + 1);
        return Integer.valueOf(number2);
    }

    private int numberOfPages() {
        if (numberOfElementsInteger() <= 24) {
            return 1;
        } else {
            return (numberOfElementsInteger() / 24) + 1;
        }
    }

    private List<Elements> elementsFromEachPage() {
        Elements elements = null;
        List<Elements> allElements = new ArrayList<>();
        Document document = null;
        for (int i = 1; i <= numberOfPages(); i++) {
            try {
                document = Jsoup.connect(link + ("&page=" + i)).get();
                elements = document.getElementsByClass("hidden-xs offer-item-price-per-m");
                allElements.add(elements);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (allElements.size() != 0) {
            return allElements;
        } else return null;
    }

    private List<String> allElementsOnEachPage() {
        List<String> stringList = new ArrayList<>();
        for (Elements elements : elementsFromEachPage()) {
            for (Element e : elements) {
                stringList.add(e.text());
            }
        }
        return stringList;
    }

    private List<Double> doublePrices() {
        return allElementsOnEachPage().stream()
                .map(x -> x.replace(" ", ""))
                .map(x -> x.substring(0, x.indexOf("z")))
                .map(x -> Double.parseDouble(x))
                .collect(Collectors.toList());
    }

    public double getAverage() {
        Double sum = 0.0;
        for (Double aDouble : doublePrices()) {
            sum += aDouble;
        }
        return rounding(sum / doublePrices().size());
    }

    private double rounding(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }
}