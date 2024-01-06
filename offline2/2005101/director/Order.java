package director;

import shakes.Shake;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    public List<Shake> shakeList=new ArrayList<>();
    public Order(int id)
    {
        System.out.println("Order is opened");
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
