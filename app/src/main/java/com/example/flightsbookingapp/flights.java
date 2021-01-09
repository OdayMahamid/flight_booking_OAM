package com.example.flightsbookingapp;

public class flights {
    public String from;
    public String dest;
    public String fromdate, todate,cost;

    public flights(String from, String dest, String fromdate, String todate, String cost) {
        this.from = from;
        this.dest = dest;
        this.fromdate = fromdate;
        this.todate = todate;
        this.cost = cost;
    }

    public flights(){

    }
   String getFrom(){
        return this.from;

   }
    String getDest(){
        return this.dest;

    }
    String getCost(){
        return this.cost;

    }
    String getFromD(){
        return this.fromdate;

    }
    String getDestD(){
        return this.todate;

    }
    @Override
    public String toString() {
        return "flights{" +
                "from='" + from + '\'' +
                ", dest='" + dest + '\'' +
                ", from_date='" + fromdate + '\'' +
                ", to_date='" + todate + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
