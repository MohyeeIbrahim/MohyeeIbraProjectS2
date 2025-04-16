package org.example;

public class Chef {
    int chefId;
    String chefName;
    String password;

    public Chef(int chefId, String chefName,String password) {
          this.chefId=chefId;
          this.chefName=chefName;
          this.password=password;
    }

    public Chef() {
    }

    public int getChefId() {
    return chefId;

    }
}
