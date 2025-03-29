package org.example;

public class Chef {
    int chefId;
    String chefName;
    String password;


    public Chef(int i, String chefJohn,String password) {
          chefId=i;
          chefName=chefJohn;
          this.password=password;

    }

    public Chef() {
    }

    public int getChefId() {
    return chefId;

    }
}
