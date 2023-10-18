import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Menu {

          private ArrayList<Meal> aktualneMenu = new ArrayList<>();
          private ArrayList<Meal> stareMenu = new ArrayList<>();

          private int menuSize;

        public void addToMenu(Meal x){ // dodaje do menu
           aktualneMenu.add(x);
           menuSize++;
        }

        public ArrayList<Meal> deleteFromMenu(Meal x) { //usuwa z manu
            stareMenu.add(x); //przerzuca usunieta pozycje do starego menu
            aktualneMenu.remove(x);
            menuSize--;
           return aktualneMenu;
        }

        public void zapisDoPliku() throws IOException, ClassNotFoundException {
            try{
                FileOutputStream writeData = new FileOutputStream("a.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                writeStream.writeObject(aktualneMenu);
                writeStream.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void odczytZPliku() throws IOException, ClassNotFoundException {
        try {
            FileInputStream readData = new FileInputStream("a.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList menu2_2 = (ArrayList<Meal>) readStream.readObject();
            readStream.close();

            menu2_2.forEach((n) -> System.out.println(n.toString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


        public void show(){
            aktualneMenu.forEach((n)-> {
                System.out.println(n.danieInfo());
            });
        }

        public void notAvailable(Meal b){
            if(aktualneMenu.add(b)) System.out.println("niedostępne");
        }


        public int getMenuSize() {
             return menuSize;
        }

        public ArrayList<Meal> getAktualneMenu(){
            return aktualneMenu;
        }


}
