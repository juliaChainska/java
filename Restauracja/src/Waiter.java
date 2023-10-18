public class Waiter extends Staff implements Runnable {
    private int id;
    private String type;
    private Order orderToDeliver;

    Waiter(String name, String surname, int id){
        super(name, surname);
        this.id = id;
        type = "Kelner";

    }

    public String infoAbout(){
        return super.getName() + " " + super.getSurname();
    }

    public void whichOrder(Order x){
        orderToDeliver = x;
    }

    public void makeDelivery() throws InterruptedException {
        System.out.println("Danie u kelnera...");
        Thread.sleep(1000);
        System.out.println("Zamówienie dostarczone!");
    }


    public void run(){
        try {
            makeDelivery();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getId(){
        return id;
    }


}
