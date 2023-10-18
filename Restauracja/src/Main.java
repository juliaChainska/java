import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main implements Serializable  {
    private static Double dziennyUtarg = 0.0;
    private static int o = 0;
    private static int employeId = 1;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        HashMap<Order, Integer> WaitingOrders = new HashMap<>();
        HashMap<Order, Integer> finishedOrders = new HashMap<>();

        kitchenSim t1 = new kitchenSim();

        Meal kurczak = new Meal("Kurczak z puree marchewkowym i szparagami", 20.1, "Danie Główne", 1);
        Meal krem = new Meal("Krem z makrchewki", 15.0, "Przystawka", 2);
        Meal ciasto = new Meal("Ciasto marchewkowe", 15.0, "Deser", 3);
        Meal makaron = new Meal("Makaron z pomidorkami", 20.0, "Danie Główne", 4);
        Meal salatka = new Meal("Sałatka z gruszką, serem i orzechami", 20.0, "Danie Główne", 5);

        Menu aktualneMenu = new Menu();
        aktualneMenu.addToMenu(kurczak);
        aktualneMenu.addToMenu(krem);
        aktualneMenu.addToMenu(ciasto);
        aktualneMenu.addToMenu(makaron);
        aktualneMenu.addToMenu(salatka);

        //Pracownicy na start
        Staff staff1 = new Staff();
        Cook c1 = new Cook("Arkadiusz", "Bąk", employeId++);
        Cook c2 = new Cook("Małgorzata", "Kra", employeId++);
        Driver d1 = new Driver("Jan", "Nowak", employeId++);
        Waiter w1 = new Waiter("Helena", "Mazur", employeId++);

        staff1.addStaff(c1);
        staff1.addStaff(c2);
        staff1.addStaff(d1);
        staff1.addStaff(w1);

        int numberOfOrders = 1;

        Order o1 = new Order("stacjo");
        o1.addToOrder(kurczak);
        o1.addToOrder(krem);
        WaitingOrders.put(o1, numberOfOrders++);
        addToTake(o1);

        Order o2 = new Order("online");
        o2.addToOrder(salatka);
        o2.addToOrder(krem);
        o2.addToOrder(ciasto);
        finishedOrders.put(o2, numberOfOrders++);
        addToTake(o2);

        Scanner scn1 = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int orderNum = numberOfOrders;

        Restaurant restaurant = new Restaurant() {
            @Override
            public boolean isOpen() {
                Scanner s1 = new Scanner(System.in);
                System.out.println("1 -> Otworz Restaurację " +
                        "\n2 -> Zamknij Restauracje");
                int s = s1.nextInt(2);
                if (s == 1) return true;
                else return false;
            }
        };

        if (restaurant.isOpen()) {
            boolean isThisTheEnd = false;
            while (isThisTheEnd == false) {
                System.out.println();
                System.out.println("Wybierz numer");
                System.out.println("1 -> Opcje Menu");
                System.out.println("2 -> Opcje zamówienia");
                System.out.println("3 -> Wylicz utarg");
                System.out.println("4 -> Personel");
                num1 = scn1.nextInt();
                switch (num1) {
                    case 1:
                        System.out.println();
                        System.out.println("Opcje Menu");
                        System.out.println("\t" + "1 -> Wyświetl menu");
                        System.out.println("\t" + "2 -> Dodaj do menu");
                        System.out.println("\t" + "3 -> Usuń z menu");
                        System.out.println("\t" + "4 -> Zapisz/Wczytaj aktualne menu do/z pliku");
                        System.out.println();

                        num2 = scn1.nextInt();
                        switch (num2) {
                            case 1: //Wyświetl menu
                                aktualneMenu.show();
                                break;
                            case 2: //Dodaj do menu
                                Scanner sss = new Scanner(System.in);
                                System.out.println("Podaj hasło: ");
                                int haslo = sss.nextInt();
                                if(haslo==123) {
                                    addNewToMenu(aktualneMenu);
                                } else System.out.println("Błędne hasło!");
                                break;
                            case 3: // Usun z menu
                                System.out.println("Ktore danie usunąć?");
                                aktualneMenu.show();
                                int remove = scn1.nextInt();
                                Meal x = null;
                                boolean isThis = false;

                                int i = 0;
                                while (!isThis && i < aktualneMenu.getMenuSize()) {
                                    x = aktualneMenu.getAktualneMenu().get(i);
                                    if (remove == x.getMealId()) {
                                        aktualneMenu.deleteFromMenu(x);
                                        isThis = true;
                                    }
                                    i++;
                                }
                                break;
                            case 4:
                                aktualneMenu.zapisDoPliku();
                                aktualneMenu.odczytZPliku();
                                break;
                        }
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Opcje zamówienia");
                        System.out.println("\t" + "1 -> Złóż zamówienia");
                        System.out.println("\t" + "2 -> Wypisz zamówienia, które czekają na realizację");
                        System.out.println("\t" + "3 -> Wypisz zamówienia, które zostały zrealizowane");
                        System.out.println("\t" + "4 -> Złóż losowe zamówienie");
                        System.out.println("Wybierz numer");
                        int num4 = scn1.nextInt();

                        switch (num4) {
                            case 1: // zloz zamowienie
                                numberOfOrders++;
                                System.out.println("Podaj typ zamówienia: " +
                                        "\n 1 -> stacjo " +
                                        "\n 2 -> online");
                                Scanner scn3 = new Scanner(System.in);
                                int orderType = scn3.nextInt(); // sprawdza typ
                                String type = null;

                                Order oo1 = new Order(type);
                                Scanner scn6 = new Scanner(System.in);

                                if (orderType == 1) {
                                    oo1.setTableNum(o++);
                                    type = "stacjo";
                                } else {
                                    type = "online";
                                    System.out.println("Podaj adres: ");
                                    String scan = scn6.nextLine();
                                    oo1.setAdress(scan);
                                }

                                Scanner scn7 = new Scanner(System.in);
                                System.out.println("Podaj numerr dania");
                                aktualneMenu.show();
                                int mealNum = scn7.nextInt();
                                Meal x = null;

                                int i = 0;
                                boolean b = false;
                                while (!b && i < aktualneMenu.getMenuSize()) {
                                    x = aktualneMenu.getAktualneMenu().get(i);
                                    if (mealNum == x.getMealId()) {
                                        oo1.addToOrder(x);
                                        b = true;
                                        break;
                                    }
                                    i++;
                                }

                                System.out.println("Koniec zamówienia?  " +
                                        "\n 1 -> Nie " +
                                        "\n 2 -> Tak");
                                int s = scn7.nextInt();
                                boolean theEnd = false;
                                if (s == 1) theEnd = false;
                                else theEnd = true;
                                while (!theEnd) {
                                    Meal a = null;
                                    System.out.println("Podaj numer dania");
                                    int mealNum2 = scn7.nextInt();
                                    b = false;
                                    while (!b && i < aktualneMenu.getMenuSize()) {
                                        x = aktualneMenu.getAktualneMenu().get(i);
                                        if (mealNum2 == x.getMealId()) {
                                            oo1.addToOrder(x);
                                            b = true;
                                        }
                                        i++;
                                    }
                                    System.out.println("Koniec zamówienia?  " +
                                            "\n 1 -> Nie " +
                                            "\n 2 -> Tak");
                                    int s2 = scn7.nextInt();
                                    if (s2 == 1) theEnd = false;
                                    else theEnd = true;
                                }

                                int numerZ = numberOfOrders;

                                WaitingOrders.put(oo1, numberOfOrders);
                                System.out.println("Zamówienie nr. " + (numberOfOrders++) + " | " + type);
                                orderNum = numberOfOrders;
                                System.out.println(oo1.getMealCountAndDate());
                                System.out.println(oo1.getPrice2() + " zł");
                                System.out.println(oo1.getOrder());
                                addToTake(oo1);


                                //kuchnia t1 = new kuchnia(numerZ, oo1);
                                t1.setNameAndOrder(numerZ, oo1);
                                t1.start();
                                double pr = oo1.getPrice2();
                                System.out.println("cena teraz: " + pr);
                                long time = t1.getHowlong();
                                System.out.println(time);
                                t1.getPrice(pr);


                                WaitingOrders.remove(oo1, orderNum);
                                numberOfOrders -= 1;
                                finishedOrders.put(oo1, orderNum);
                                break;

                            case 2: //wypisz zamowienia czekajace
                                showWaitingOrders(WaitingOrders);
                                break;

                            case 3: // wypisz zamowienia zrealizowane
                                showFinishedOrders(finishedOrders);
                                break;

                            case 4: // losowe zamówienie
                                numberOfOrders++;
                                Order oo2 = new Order(losowyTypZamowienia());
                                //int ilosc = losowaIloscPozycji();
                                int ilosc = (int)Math.floor(Math.random()*(6-1+1)+1);

                                Random rand = new Random();
                                //int numer = rand.nextInt(5) + 1;
                                int numer = (int)Math.floor(Math.random()*(6-1+1)+1) ;
                                int f = 0;
                                while (f < ilosc) {
                                    x = aktualneMenu.getAktualneMenu().get(f);
                                    if (numer == x.getMealId()) {
                                        oo2.addToOrder(x);
                                    }
                                    f++;
                                }
                                WaitingOrders.put(oo2, numberOfOrders);

                                System.out.println(oo2.getMealCountAndDate());
                                System.out.println(oo2.getOrderInfo());

                                //wykonywanie threadow
                                int numerZ2 = numberOfOrders;
                                t1.setNameAndOrder(numerZ2,oo2);
                                t1.start();

                                if(t1.getIsReady()) {
                                    if (losowyTypZamowienia().equals("online")) {
                                        startDelivery(d1, oo2);
                                    } else startWaiter(w1, oo2);
                                }


                                WaitingOrders.remove(oo2, orderNum);
                                numberOfOrders -= 1;
                                finishedOrders.put(oo2, orderNum);
                                break;
                        }
                        break;

                    case 3: // wylicz utarg
                        showTake();
                        System.out.println();
                        break;

                    case 4: //pracownicy
                        System.out.println();
                        System.out.println("Wybierz numer: ");
                        System.out.println("\t" + "1 -> Zatrudnij nowego pracownika");
                        System.out.println("\t" + "2 -> Zwolnij pracownika");
                        System.out.println("\t" + "3 -> Informacje o pracowniku");
                        System.out.println("\t" + "4 -> Wypisz wszystkich pracowników");
                        System.out.println();
                        int xx = scn1.nextInt();
                        switch (xx) {
                            case 1: // zatrudnij
                                Staff staff = new Staff();
                                System.out.println("Podaj typ pracownika: ");
                                System.out.println("\t" + "1 -> Kucharz");
                                System.out.println("\t" + "2 -> Kelner");
                                System.out.println("\t" + "3 -> Dostawca");
                                int xx2 = scn1.nextInt();
                                System.out.println("Podaj imię pracownika: ");
                                String name = scn1.next();
                                System.out.println("Podaj nazwisko pracownika: ");
                                String surname = scn1.next();
                                if (xx2 == 1) {
                                    Cook cc1 = new Cook(name, surname, employeId++);
                                    staff1.addStaff(cc1);
                                } else if (xx2 == 2) {
                                    Waiter ww1 = new Waiter(name, surname, employeId++);
                                    staff1.addStaff(ww1);
                                } else {
                                    Driver dd1 = new Driver(name, surname, employeId++);
                                    staff1.addStaff(dd1);
                                }
                                break;
                            case 2: //zwolnij pracownika
                                System.out.println("Wprowadz nr pracownika do zwolnienia");
                                System.out.println("1 -> Kucharz nr.1");
                                System.out.println(c1.infoAbout());
                                System.out.println("2 -> Kucharz nr.2");
                                System.out.println("3 -> Kelner");
                                System.out.println("4 -> Dostawca");
                                int empNum = scn1.nextInt(4);
                                staff1.isEmpty();
                                if (empNum == 1) staff1.removeStaff(c1);
                                else if (empNum == 2) staff1.removeStaff(c2);
                                else if (empNum == 3) staff1.removeStaff(w1);
                                else if (empNum == 4) staff1.removeStaff(d1);
                                else System.out.println("Błędny numer");

                                break;
                            case 3: // informacje o pracowniku
                                System.out.println("Wprowadz nr pracownika");
                                System.out.println("1 -> Kucharz nr.1");
                                System.out.println("2 -> Kucharz nr.2");
                                System.out.println("3 -> Kelner");
                                System.out.println("4 -> Dostawca");

                                int a = scn1.nextInt();
                                if (a == 1) {
                                    Cook x = c1;
                                    System.out.println("Kucharz - " + x.infoAbout() + " id: " + x.getId());
                                } else if (a == 2) {
                                    Cook x = c2;
                                    System.out.println("Kucharz - " + x.infoAbout() + " id: " + x.getId());
                                } else if (a == 3) {
                                    Waiter x = w1;
                                    System.out.println("Kelner - " + x.infoAbout() + " id: " + x.getId());
                                } else {
                                    Driver x = d1;
                                    System.out.println("Dostawca - " + x.infoAbout() + " id: " + x.getId());
                                }
                                System.out.println();

                                break;
                            case 4: // info o wszystkich pracownikach
                                staff1.infoAboutStaff();
                                break;
                        }
                        break;
                }


                System.out.println();
                System.out.println("Zakończyć działanie programu? " +
                        "\n 1 -> NIE " +
                        "\n 2 -> TAK");

                int sss = scn1.nextInt();
                if (sss == 1) isThisTheEnd = false;
                else isThisTheEnd = true;
                System.out.println();
            }
        }
    }

    public static Double addToTake(Order x) {
        Double temp = x.getPrice2();
        return dziennyUtarg += temp;
    }

    public static void showTake() {
        System.out.println("Utarg: " + dziennyUtarg);
    }

    public static String losowyTypZamowienia() {
        String wynik = null;
        Random num = new Random();
        int los = num.nextInt(2);
        String orderStacjo = "stacjo";
        String orderOnline = "online";
        if (los == 1) return orderStacjo;
        else return orderOnline;
    }

    public static int losowaIloscPozycji() {
        Random num = new Random();
        int los = num.nextInt(6) + 1;
        return los;
    }
    public static void showWaitingOrders(HashMap<Order, Integer> list) {
        list.forEach((n, orderid) -> {
            System.out.println("\n" + "Zamówienie nr. " + orderid + " | " + n.getOrderInfo());
        });
    }

    public static void showFinishedOrders(HashMap<Order, Integer> list) {
        list.forEach((n, orderid) -> {
            System.out.println("\n" + "Zamówienie nr. " + orderid + " | " + n.getOrderInfo());
        });
    }


//    public static void startCooking(Cook c, Order o) throws InterruptedException {
//        c.whichOrder(o);
//        Thread t1 = new Thread(c);
//        t1.start();
//        t1.join();
//    }

    public static void startDelivery(Driver d, Order o) throws InterruptedException {
        Thread t3 = new Thread(d);
        t3.start();
        t3.join();
    }

    public static void startWaiter(Waiter w, Order o) throws InterruptedException {
        Thread t4 = new Thread(w);
        t4.start();
        t4.join();
    }

    public static void addNewToMenu(Menu x){
        Scanner s2 = new Scanner(System.in);
        System.out.println("Podaj nazwę nowego dania");
        String nazwa = s2.nextLine();
        System.out.println("Podaj cene");
        Double cena = s2.nextDouble();
        System.out.println("Podaj typ (danie głowne/deser...");
        String typ = s2.next();
        System.out.println("Podaj id dania");
        int id = s2.nextInt();
        Meal nowe = new Meal(nazwa, cena, typ, id);
        x.addToMenu(nowe);
    }
}
