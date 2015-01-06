package com.bf.christmas.model;
 
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class Present {
 
    private int id;
    private String name;
    private String description;
    private double price;
    private User owner;
    private List<User> buyers;
    
    private double percentage;
 
    public Present() {}
 
    public Present(String name, String description, double price) {
        this.name  = name;
        this.description = description;
        this.price=price;
    }
    public Present(String name, String description, double price, User owner) {
        this.name  = name;
        this.description = description;
        this.price=price;
        this.owner=owner;
    }
    
    public Present(int id, String name, String description, double price) {
        this.id = id;
        this.name  = name;
        this.description = description;
        this.price=price;
    }
    
    public Present(int id, String name, String description, double price, double percentage) {
        this.id = id;
        this.name  = name;
        this.description = description;
        this.price=price;
        this.percentage=percentage;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }
    
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    
    
}