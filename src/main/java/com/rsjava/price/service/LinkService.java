package com.rsjava.price.service;

public class LinkService {

    private String city;
    private int number;

    public LinkService(String city, int number) {
        this.city = city;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLink() {

        if (city.equalsIgnoreCase("LODZ")) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "lodz/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bdescription%5D=1&search" +
                    "%5Bregion_id%5D=5&search%5Bcity_id%5D=1004&nrAdsPerPage=24";
        } else if (city.equalsIgnoreCase("WARSZAWA")) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "warszawa/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bregion_id%5D=7&search%" +
                    "5Bsubregion_id%5D=197&search%5Bcity_id%5D=26&nrAdsPerPage=24";
        } else if (city.equalsIgnoreCase("KRAKOW")) {
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "krakow/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bdescription%5D=1&search%" +
                    "5Bregion_id%5D=6&search%5Bsubregion_id%5D=410" +
                    "&search%5Bcity_id%5D=38&nrAdsPerPage=24";
        } else if (city.equalsIgnoreCase("WROCLAW")){
            return "https://www.otodom.pl/sprzedaz/mieszkanie/" +
                    "wroclaw/?search%5Bfilter_float_m%3Afrom%5D=" +
                    number + "&search%5Bfilter_float_m%3Ato%5D=" +
                    number + "&search%5Bdescription%5D=1&search%5" +
                    "Bregion_id%5D=1&search%5Bsubregion_id%5D=381" +
                    "&search%5Bcity_id%5D=39&nrAdsPerPage=24";
        }
        return null;
    }
}
