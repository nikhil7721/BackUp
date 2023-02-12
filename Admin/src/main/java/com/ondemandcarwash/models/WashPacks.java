package com.ondemandcarwash.models;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;



@Document(collection="WashPackages")
public class WashPacks {

@Id
int id;
String name;
int cost;
String description;

public int getCost() {
return cost;
}
public void setCost(int cost) {
this.cost = cost;
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
public void setId(int id) {
this.id = id;
}
public WashPacks(int id, String name, int cost, String description) {
super();
this.id = id;
this.name = name;
this.cost = cost;
this.description = description;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
@Override
public String toString() {
return "WashPacks [id=" + id + ", name=" + name + ", cost=" + cost + ", description=" + description + "]";
}

public WashPacks(){

}

}