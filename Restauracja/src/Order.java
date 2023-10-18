import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Order {
    private ArrayList<Meal> order = new ArrayList<>();
    private String orderType; // stacjonarne czy online
    private int mealCount; // ile dań ma zamówienie
    private Date dateCreated;

    private int tableNum; // numer stolika
    private String adress; // adres dostawy

    private double price = 0; // cena za cale zamowienie


    static int orderCount = 0;

    {
        orderCount++;

    }

    Order(String orderType){
        this.orderType = orderType;
        dateCreated = new Date();
    }


    private ArrayList<Order> finishedOrders = new ArrayList<>(); //?
    private ArrayList<Order> toDoOrders = new ArrayList<>();

    public void addToOrder(Meal x){
        order.add(x);
        mealCount++;
    }
    public ArrayList<Meal> removeFromOrder(Meal x){
        order.remove(x);
        mealCount--;
        return order;
    }

    public void addToFinishedOrder(Order x){
        finishedOrders.add(x);
    }

    public static int getOrderCount(){
        return orderCount;
    }

    public int getOrderCount2(){
        return orderCount;
    }

    public String getOrderInfo() {
        String listString = order.stream().map(Object::toString)
                .collect(Collectors.joining(", \n"));

        if (orderType == "stacjo") {
            return  orderType + "\n" + listString;

        } else return orderType + "\n" + listString;
    }


    public String getMealCountAndDate(){
        return "ilość pozycji: " + mealCount + " | data: " + dateCreated;
    }

    public int getMealCount(){
        return mealCount;
    }

    public void showOrder(){
        order.forEach((n)-> {
            System.out.println(n.danieInfo());
        });
    }

    public double getPrice2(){
        order.forEach((n)->{
            price = price + n.getPrice();
        });
        return price;
    }

    public void showFinishedOrder(){
        System.out.println("Zamówienia skończone");
        finishedOrders.forEach((n)-> {
            System.out.println(n.getOrderInfo());
            System.out.println();
        });
    }

    public ArrayList<Meal> getOrder(){
        return order;
    }

    public String setAdress(String adres){
        return adres = adress;
    }

    public int setTableNum (int tblNum){
        return tblNum = tableNum;
    }
}
