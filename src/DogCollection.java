
//Virginia Anyim vian2657
import java.util.ArrayList;

public class DogCollection {

    private ArrayList<Dog> dogs = new ArrayList<>(); // Lista som håller hundarna i samlingen

    // Lägg till en ny hund i samlingen
    public boolean addDog(Dog dog) {
        if (dog == null) {
            return false; // Om hunden är null returnera false
        }
        if (containsDog(dog)) { // Kontroll om hunden redan finns i samlingen
            return false;
        } else {
            dogs.add(dog);
            return true;
        }
    }

    // Ta bort en hund med det angivna namnet från samlingen
    public boolean removeDog(String dogName) {
        if (dogName == null) {
            return false; // Om hundens namn är null returnera false
        }
        for (int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).getName().equals(dogName) && dogs.get(i).getOwner() == null) {
                dogs.remove(i); // Ta bort hunden om dess ägare är null
                return true;
            }
        }
        return false;
    }

    // Ta bort en specifik hund från samlingen
    public boolean removeDog(Dog dog) {
        for (int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).equals(dog) && dogs.get(i).getOwner() == null) {
                dogs.remove(i); // Ta bort hunden om ägare är null
                return true;
            }
        }
        return false;
    }

    // Kontrollera om samlingen innehåller en hund med det angivna namnet
    public boolean containsDog(String dogName) {
        return getDog(dogName) != null;
    }

    // Kontrollera om samlingen innehåller en specifik hund
    public boolean containsDog(Dog dog) {
        for (Dog existingDog : dogs) {
            if (existingDog.getName().equals(dog.getName())) {
                return true;
            }
        }
        return false;
    }

    // Hämta en hund med det angivna namnet från samlingen
    public Dog getDog(String dogName) {
        for (int i = 0; i < dogs.size(); i++) {
            Dog newDog = dogs.get(i);
            if (newDog.getName().equals(dogName)) {
                return newDog; // Returnera hunden om den hittas i samlingen
            }
        }
        return null;
    }

    // Hämta en kopia av hundarna i samlingen, sorterade efter namn med dogSorter
    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogs); // Skapa kopia av hundarna i samlingen
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs); // Använd dogSorter för att sortera hundarna efter namn
        return sortedDogs;
    }

    // Filtrera hundarna i samlingen efter svanslängd och returnera en sorterad
    // lista
    public ArrayList<Dog> filterDogsByTailLength(double tailLength) {
        ArrayList<Dog> selectedDogs = new ArrayList<>();

        for (Dog dog : dogs) {
            if (dog.getTailLength() >= tailLength) {
                selectedDogs.add(dog); // Lägg till hunden om dess svanslängd är större eller lika med den angivna
                                       // svanslängden
            }
        }

        // Sortera utvalda hundarna efter svanslängd och namn med dogSorter
        DogSorter.sortDogs(new DogTailNameComparator(), selectedDogs);

        return selectedDogs; // Returnera den sorterade listan med utvalda hundar
    }
}