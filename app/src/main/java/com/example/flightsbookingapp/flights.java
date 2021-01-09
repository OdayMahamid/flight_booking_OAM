package com.example.flightsbookingapp;

public class flights {
    private String from;
    private String To;
    private String day;
    private String month;
    private String year;
    private String dep_time;
    private String lan_time;
    private String Price;
    private String Date;


    public flights() {
    }

    public flights(String from, String dest, String day, String month, String year, String dep_time, String lan_time, String price) {
        this.from = from;
        this.To = dest;
        this.day = day;
        this.month = month;
        this.year = year;
        this.dep_time = dep_time;
        this.lan_time = lan_time;
        this.Price=price;
        this.Date=day+"."+month+"."+year;
    }
    public flights(String from, String dest, String day,String month, String year,String price) {
        this.from = from;
        this.To = dest;
        this.Price=price;
        this.day=day;
        this.month=month;
        this.year=year;
        setDate(day, month, year);
    }
    public flights(String from, String dest, String date, String price) {
        this.from = from;
        this.To = dest;
        this.Price=price;
        this.Date=date;
        setdate1(date);
    }
    public void  setdate1(String date)
    {
        String[] split= date.split(".");
        this.day=split[0];
        this.month=split[1];
        this.year=split[2];
    }
    public void setDate(String day, String month, String year)
    {
        this.Date=day+"."+month+"."+year;
    }
    public String getDate()
    {
        return this.Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrice() { return Price; }

    public void setPrice(String price) { Price = price; }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getTo() { return To; }

    public void setTo(String dest) { this.To= dest; }

    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getDep_time() { return dep_time; }

    public void setDep_time(String dep_time) { this.dep_time = dep_time; }

    public String getLan_time() { return lan_time; }

    public void setLan_time(String lan_time) { this.lan_time = lan_time; }
}
