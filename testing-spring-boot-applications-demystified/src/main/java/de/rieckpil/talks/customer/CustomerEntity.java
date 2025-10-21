package de.rieckpil.talks.customer;

public class CustomerEntity {

  private String id;

  private String name;

  CustomerEntity() {
  }

  CustomerEntity(String name) {
    this.name = name;
  }

  String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }
}
