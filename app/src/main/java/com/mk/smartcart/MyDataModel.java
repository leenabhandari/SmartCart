package com.mk.smartcart;

/**
 * Created by leena on 13-09-2017.
 */


public class MyDataModel {

    private String id;

    private String name;

    private String image;

    private String email;

  MyDataModel(String name,String email)
  {
      this.name=name;
      this.email=email;
  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id+" "+name+" "+image+ " "+ email;
    }
}