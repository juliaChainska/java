import java.util.ArrayList;

public class Staff {
    private String name;
    private String surname;
    private long phoneNum;
    //private String type;
    private ArrayList<Staff> pracownicy = new ArrayList<>();

    Staff(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    Staff(){

    }

    public void addStaff(Staff x){
        pracownicy.add(x);
    }

    public void removeStaff(Staff x){
        pracownicy.remove(x);
    }

    public void isEmpty(){
        if(pracownicy.isEmpty()) System.out.println("Błąd! Brak pracowników");
    }
    public void setPhoneNum(long num){
        phoneNum = num;
    }

    public void infoAboutStaff(){
        pracownicy.forEach((n)->{
            System.out.println("- " + n.name +" "+n.surname);
        });
    }


    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }


}
