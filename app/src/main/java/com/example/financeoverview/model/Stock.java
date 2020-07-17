package com.example.financeoverview.model;

public class Stock {

    private String symbol;
    private String name;
    private String type;
    private String region;
    private String marketOpen;
    private String markedClose;
    private String timezone;
    private String currency;
    private String matchScore;
    private double open;
    private double high;
    private double low;
    private double price;
    private long volume;
    private String latest_trading_day;
    private double previous_close;
    private double change;
    private String change_percent;

    public Stock(String symbol, String name, String type, String region, String marketOpen, String markedClose, String timezone, String currency, String matchScore) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.region = region;
        this.marketOpen = marketOpen;
        this.markedClose = markedClose;
        this.timezone = timezone;
        this.currency = currency;
        this.matchScore = matchScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMarketOpen() {
        return marketOpen;
    }

    public void setMarketOpen(String marketOpen) {
        this.marketOpen = marketOpen;
    }

    public String getMarkedClose() {
        return markedClose;
    }

    public void setMarkedClose(String markedClose) {
        this.markedClose = markedClose;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getLatest_trading_day() {
        return latest_trading_day;
    }

    public void setLatest_trading_day(String latest_trading_day) {
        this.latest_trading_day = latest_trading_day;
    }

    public double getPrevious_close() {
        return previous_close;
    }

    public void setPrevious_close(double previous_close) {
        this.previous_close = previous_close;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(String change_percent) {
        this.change_percent = change_percent;
    }
}
