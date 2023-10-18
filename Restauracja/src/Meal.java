import java.io.Serializable;

public class Meal implements Serializable {

    private String name;
    private Double price;
    private String type; //kategoria dania
    private int mealId; // id dania
    //private int cookTime; //czas przygotowania
    private String description; // opis dania



    Meal(String name, Double price, String type, int mealId){
        this.name = name;
        this.price = price;
        this.type = type;
        this.mealId = mealId;
        //this.cookTime = cookTime;
    }

    Meal(String name, Double price, String type, int mealId, String description){
        this.name = name;
        this.price = price;
        this.type = type;
        this.mealId = mealId;
       // this.cookTime = cookTime;
        this.description = description;
    }


    public String getName(){
        return this.name;
    }

    public Double getPrice(){
        return this.price;
    }

    public String danieInfo(){
        return getMealId() + ": " + name + " " + price + " - " + type;
    }

    public String toString(){
        return name;
    }

    public int getMealId(){
        return mealId;
    }

}
