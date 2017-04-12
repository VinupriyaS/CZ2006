package com.example.hyunjeong.district132;

import java.util.Random;

public class PostDB {

    private int post_id;
    private String username;
    private String location;
    private String housetype;
    private String purpose;
    private int price;
    private int no_of_rooms;
    private String furnishing;
    private String facilities;
    private String desc;
    private double size;
    private String address;
    public PostDB() {

    }

    public PostDB(String username, String location, String housetype, String purpose, int price,
                  int no_of_rooms, String furnishing, String facilities, String desc, double size) {
        this.post_id = generatePostID();
        this.username = username;
        this.location = location;
        this.housetype = housetype;
        this.purpose = purpose;
        this.price = price;
        this.no_of_rooms = no_of_rooms;
        this.furnishing = furnishing;
        this.facilities = facilities;
        this.desc = desc;
        this.size = size;
    }

   public void setPost_id(int post_id) {
        this.post_id = generatePostID();
    }

    public int getPost_id() {
        return post_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getHousetype(){
        return housetype;
    }

    public void setHousetype(String housetype){
        this.housetype = housetype;
    }

    public String getPurpose(){
        return purpose;
    }

    public void setPurpose(String purpose){
        this.purpose = purpose;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }

    public String getFurnishing() {
        return furnishing;
    }

    public void setFurnishing(String furnishing) {
        this.furnishing = furnishing;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setAddress(String address){this.address=address;}

    public String getAddress(){return address;}

    public int generatePostID(){
        Random rand = new Random();
        int min = 1000;
        int max = 9999;
        int n = rand.nextInt((max-min)+1)+min;
        return n;
    }
}
