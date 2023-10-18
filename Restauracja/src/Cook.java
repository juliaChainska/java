import java.util.ArrayList;

public class Cook extends Staff {
    private int id;
    private boolean isCookAvailable;
    private boolean isOrderReady;
    private Order orderToPrepere;
    private String type;
    private long howlong;


    Cook(String name, String surname, int id){
        super(name, surname);
        this.id = id;
        type = "Kucharz";
    }

    public int getId(){
        return id;
    }

    public void whichOrder(Order x){
        orderToPrepere = x;
    }
//
//    public void makeFood(Order x) throws InterruptedException {
//        Thread.sleep(3000);
//    }
//
//    public void run() {
//        long startTime = System.currentTimeMillis();
//        isOrderReady = false;
//        System.out.println("Przygotowywanie dania...");
//        orderToPrepere.getOrder().forEach((n) -> {
//            try {
//                makeFood(orderToPrepere);
//                isCookAvailable = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        isCookAvailable = true;
//        long endTime = System.currentTimeMillis();
//        isOrderReady = true;
//        howlong = (endTime-startTime)/1000;
//        System.out.println("Zamówienie jest gotowe!");
//        System.out.println();
//
//        ArrayList<Meal> q = orderToPrepere.getOrder();
//        double totalCost = q.stream().mapToDouble(Meal::getPrice).sum();
//        double priceLater = totalCost;
//        if(howlong>15) {
//            System.out.println("Przygotowywanie zamówienia trwało:  " + howlong);
//            priceLater = priceLater - (0.20 * priceLater);
//            System.out.println("Cenę obniżono o 20%. Cena: " + priceLater);
//        }
//    }

        public boolean getIsCookAvailable(){
        return isCookAvailable;
    }


        public String infoAbout(){
            return super.getName() + " " + super.getSurname();
        }

        public String getType(){
        return type;
        }

    }
