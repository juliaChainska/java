public class Driver extends Staff implements Runnable{
    private int id;
    private String type;
    private Order orderToDeliver;

    Driver(String name, String surname, int id){
        super(name, surname);
        this.id = id;
        type = "Dostawca";
    }

    public void whichOrder(Order x){
        orderToDeliver = x;
    }

    public void makeDelivery() throws InterruptedException {
        System.out.println("Twoje danie jest w drodze...");
        Thread.sleep(7000);
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
    public String infoAbout(){
        return super.getName() + " " + super.getSurname();
    }
}

