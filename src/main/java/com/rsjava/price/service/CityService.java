package com.rsjava.price.service;

import com.rsjava.price.model.City;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class CityService {

    private City city;
    private int number;
    private String link;
    private int numberOfPages;
    private List<Elements> elementsFromEachPage;
    private List<String> allElementsOnEachPage;
    private List<Double> doubleList;

    public CityService() {
    }

    public CityService(City city, int number) throws Exception {
        this.link = getLink(city, number);
        numberOfPages = numberOfPages();
        elementsFromEachPage = elementsFromEachPage();
        allElementsOnEachPage = allElementsOnEachPage();
        doubleList = doublePrices();
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String numberOfElements() {
        Elements elements2 = null;
        try {
            Document document = Jsoup.connect(link).get();
            elements2 = document.getElementsByClass("offers-index pull-left text-nowrap");
        } catch (IOException e) {
            e.printStackTrace();
        } return elements2.get(0).text();
    }

    public int numberOfElementsInteger(){
        if (numberOfElements() != null) {
            String number = numberOfElements().replace(" ", "");
            String number2 = number.substring(number.indexOf(":") + 1);
            return Integer.valueOf(number2);
        } else {
            return 1;
        }
    }

    public int numberOfPages() throws Exception {
        if (numberOfElementsInteger() == 0){
            throw new Exception("zero");
        }
        else if (numberOfElementsInteger() <= 24) {
            return 1;
        } else {
            return (numberOfElementsInteger() / 24) + 1;
        }
    }

    public List<Elements> elementsFromEachPage() {
        Elements elements = null;
        List<Elements> allElements = new ArrayList<>();
        Document document = null;
        for (int i = 1; i <= numberOfPages; i++) {
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

    public List<String> allElementsOnEachPage() {
        List<String> stringList = new ArrayList<>();
        for (Elements elements : elementsFromEachPage) {
            for (Element e : elements) {
                stringList.add(e.text());
            }
        }
        return stringList;
    }

    public List<Double> doublePrices() {
        return allElementsOnEachPage.stream()
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

    public String getLink(City city, int number) {

        if (city == City.LODZ) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "lodz/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bdescription%5D=1&search" +
                    "%5Bregion_id%5D=5&search%5Bcity_id%5D=1004&nrAdsPerPage=24";
        } else if (city == City.WARSZAWA) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "warszawa/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bregion_id%5D=7&search%" +
                    "5Bsubregion_id%5D=197&search%5Bcity_id%5D=26&nrAdsPerPage=24";
        } else if (city == City.KRAKOW) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "krakow/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bdescription%5D=1&search%" +
                    "5Bregion_id%5D=6&search%5Bsubregion_id%5D=410" +
                    "&search%5Bcity_id%5D=38&nrAdsPerPage=24";
        }
        return null;
    }
}