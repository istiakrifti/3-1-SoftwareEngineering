package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Administrator {

    private List<Stock> stockList;
    private List<Map.Entry<Stock,String>> notification;
    public Administrator()
    {
        stockList = new ArrayList<>();
        notification = new ArrayList<>();
    }

    public void increasePrice(String name, double price) throws Exception {
        for(int i=0;i<stockList.size();i++) {
            if(stockList.get(i).getName().equalsIgnoreCase(name)) {
                Stock st = stockList.get(i);
                double prePrice = st.getPrice();
                double currPrice = prePrice + price;
                st.setPrice(currPrice);
                String msg = "For Stock " + st.getName() + " price is increased to "+currPrice+" from "+prePrice;
                st.notifyUsers(msg);

//                return msg;
            }
        }
//        return "";
    }

    public void decreasePrice(String name,double price) throws Exception {
        for(int i=0;i<stockList.size();i++) {
            if(stockList.get(i).getName().equalsIgnoreCase(name)) {
                Stock st = stockList.get(i);
                double prePrice = st.getPrice();
                double currPrice = prePrice - price;
                st.setPrice(currPrice);
                String msg = "For Stock " + st.getName() + " price is decreased to "+currPrice+" from "+prePrice;
                st.notifyUsers(msg);
//                return msg;
            }
        }
//        return "";
    }

    public void changeInStockCount(String name,int count) throws Exception {
        for(int i=0;i<stockList.size();i++) {
            if(stockList.get(i).getName().equalsIgnoreCase(name)) {
                Stock st = stockList.get(i);
                int preCount = st.getCount();
                int currCount = count ;
                st.setPrice(currCount);
                String msg = "For Stock " + st.getName() + " stock count is changed to "+currCount+" from "+preCount;
                st.notifyUsers(msg);
//                return msg;
            }
        }
//        return "";
    }

    public Stock getStock(String name) {
        for(int i=0;i<stockList.size();i++) {
            if(stockList.get(i).getName().equalsIgnoreCase(name)) {
                return stockList.get(i);
            }
        }
        return null;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<Map.Entry<Stock, String>> getNotification() {
        return notification;
    }

    public void setNotification(List<Map.Entry<Stock, String>> notification) {
        this.notification = notification;
    }
}
