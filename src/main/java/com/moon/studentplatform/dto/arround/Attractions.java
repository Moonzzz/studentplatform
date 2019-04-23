package com.moon.studentplatform.dto.arround;


public class Attractions {

  private long id;
  private String name;
  private String position;
  private String description;
  private String photos;

  public Attractions() {
  }

  public Attractions(long id, String name, String position, String description, String photos) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.description = description;
    this.photos = photos;
  }

  public Attractions(long id, String name, String position, String description) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.description = description;
  }

  public Attractions(String name, String position, String description, String photos) {
    this.name = name;
    this.position = position;
    this.description = description;
    this.photos = photos;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getPhotos() {
    return photos;
  }

  public void setPhotos(String photos) {
    this.photos = photos;
  }

}
