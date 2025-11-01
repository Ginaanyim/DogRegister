//Virginia Anyim vian2657
import java.util.Comparator;
import java.util.ArrayList;

public class DogSorter {

   //metod för att byta plats på två hundar
    private static void swapDogs(ArrayList<Dog> dogs, int firstDog, int secondDog) {  
        Dog temporaryDog = dogs.get(firstDog); //temporär variabel
        dogs.set(firstDog, dogs.get(secondDog));
        dogs.set(secondDog, temporaryDog);

    }
    // Metod för att hitta index för nästa hund baserat på comparator
    private static int nextDog (Comparator<Dog> comparator, ArrayList<Dog> dogList, int index){
        Dog currentMin = dogList.get(index);
        int currentMinIndex = index;

        for (Dog dog : dogList.subList(index + 1, dogList.size())) {
            // Jämför hund på aktuell position med den nuvarande min-hunden
            if (comparator.compare(currentMin, dog) > 0) {
                // Uppdatera min-hunden och dess index om den nya hunden är mindre
                currentMin = dog;
                currentMinIndex = dogList.indexOf(dog);
            }
        }
        return currentMinIndex;
    }
    // Metod för att sortera hundar med hjälp av comparator
     public static int sortDogs(Comparator <Dog> comparator, ArrayList <Dog> dogList){
        int counterSwaps = 0;

        for (Dog dog : dogList){
        // Hitta index för nästa hund som ska bytas plats
            int currentMinIndex = nextDog(comparator, dogList, dogList.indexOf(dog));

            if (currentMinIndex != dogList.indexOf(dog)){
                // Byt plats på hundarna om det finns en mindre hund
                swapDogs(dogList, dogList.indexOf(dog), currentMinIndex);
                counterSwaps++;
            }
        }
        return counterSwaps;
    
     } 
}
    
