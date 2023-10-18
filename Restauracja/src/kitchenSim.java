public class kitchenSim implements Runnable {
    private Thread t;
    private int threadName; // zamowienie nr
    static long howlong;
    private Order orderToPrepare;
    private double price = 0;
    boolean isReady;


    public void setNameAndOrder(int name, Order orderToPrepare) {
        threadName = name;
        this.orderToPrepare = orderToPrepare;
    }

    public void run() {
        isReady = false;
        long startTime = System.currentTimeMillis();

        try {
            System.out.println("Zamowienie nr.: " + threadName + " w trakcie realizacji");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Zamowienie nr.: " + threadName + " interrupted.");
        }

        long endTime = System.currentTimeMillis();
        howlong = (endTime - startTime) / 1000;
        System.out.println("Zamowienie nr.: " + threadName + " zakonczone i trwało: " + howlong + " sek.");
        isReady = true;
    }

    public void start() {
        System.out.println("Zaczynamy zamowienie nr.: " + threadName);
        if (t == null) {
            t = new Thread(this, String.valueOf(threadName));
            t.start();
        }
    }

    public long getHowlong() {
        return howlong;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public double znizka() {
        double price = orderToPrepare.getPrice2();
        price = price - (0.2 * price);
//        System.out.println("Przygotowywanie zamówienia trwało:  " + howlong);
//        System.out.println("Cenę obniżono o 20%. Cena: " + price);
        return price;
    }

    public double price(){
        double regularPrice = orderToPrepare.getPrice2();
        return regularPrice;
    }

    public double getPrice2(){
        if(howlong>2){
            double zniz = znizka();
            System.out.println("Przygotowywanie zamówienia trwało:  " + howlong);
            System.out.println("Cenę obniżono o 20%. Cena: " + zniz);
            return zniz;
        } else return price();
    }

    public double getPrice(double pr){
        if(howlong>2){
            pr = pr - (0.2 * pr);
           // System.out.println("Przygotowywanie zamówienia trwało:  " + howlong);
            System.out.println("Cenę obniżono o 20%. Cena: " + pr);
            return pr;
        } else return pr;
    }

}

