
// Virginia Anyim vian2657
import java.util.ArrayList;
import java.util.Arrays;

public class OwnerCollection {

    private Owner[] ownerArray; // arrayen med ägarna
    private int ownerSize;

    // konstruktor skapas med en tom array
    public OwnerCollection() {
        this.ownerArray = new Owner[0];
        this.ownerSize = 0;
    }

    public boolean addOwner(Owner owner) {
        // Kontrollera om ägaren är null eller om en ägare med samma namn redan finns
        if (owner == null || containsOwner(owner.getName())) {
            return false; // Ägaren kan inte läggas till
        }

        // Kontrollera om arrayen behöver öka i storlek
        checkOwnerCapacity();

        // Lägg till ägaren och öka storleken på arrayen
        ownerArray = Arrays.copyOf(ownerArray, ownerSize + 1);
        ownerArray[ownerSize++] = owner;
        return true;
    }

    public boolean removeOwner(String ownerName) {
        boolean removedOwner = false;

        // om ägare finns och ej har hundar
        if (containsOwner(ownerName) && getOwner(ownerName).getDogs().isEmpty()) {

            // ny array med minskad storlek
            Owner[] newArray = new Owner[ownerArray.length - 1];
            int newArrayIndex = 0;

            // loopa igenom för att kopiera ägare till ny array
            for (int i = 0; i < ownerSize; i++) {
                Owner owner = ownerArray[i];

                if (!owner.getName().equals(ownerName)) {
                    newArray[newArrayIndex++] = owner; // Kopiera ägare om det inte är den som ska tas bort
                } else {
                    removedOwner = true;
                }
            }

            // uppdatera ny array och minska storlek
            ownerArray = newArray;
            ownerSize--;
        }
        return removedOwner;
    }

    public boolean removeOwner(Owner owner) {
        boolean removedOwner = false;

        // Om ägaren finns och inte har hundar
        if (containsOwner(owner) && owner.getDogs().isEmpty()) {
            Owner[] newArray = new Owner[ownerArray.length - 1];
            int newArrayIndex = 0;

            for (int i = 0; i < ownerSize; i++) {
                // kopiera över ägare om just den inte ska tas bort
                if (!ownerArray[i].equals(owner)) {
                    newArray[newArrayIndex++] = ownerArray[i];
                } else {
                    removedOwner = true;
                }
            }

            // Uppdatera arrayen och minska storleken
            ownerArray = newArray;
            ownerSize--;
        }
        return removedOwner;
    }

    // Kontroll om arrayen innehåller en viss ägare
    public boolean containsOwner(String ownerName) {
        // Loopa igenom arrayen för att söka efter ägaren med det givna namnet
        for (int i = 0; i < ownerSize; i++) {
            if (ownerArray[i].getName().equals(ownerName)) {
                return true;
            }
        }
        return false;
    }

    // Kontroll om innehåller samlingen en viss ägare
    public boolean containsOwner(Owner owner) {
        for (int i = 0; i < ownerSize; i++) {
            if (ownerArray[i].equals(owner)) {
                return true;
            }
        }
        return false;
    }

    // Hämta ägare med det givna namnet från samlingen
    public Owner getOwner(String ownerName) {
        for (int i = 0; i < ownerSize; i++) {
            if (ownerArray[i].getName().equals(ownerName)) {
                return ownerArray[i]; // Returnera ägaren om den hittas
            }
        }
        return null; // Returnerar null om ägaren inte hittas
    }

    // kopia av array med aktuell storlek och sortera den
    public ArrayList<Owner> getOwners() {
        Owner[] sortedOwners = Arrays.copyOf(ownerArray, ownerSize);
        Arrays.sort(sortedOwners);
        // Array som ArrayList
        return new ArrayList<>(Arrays.asList(sortedOwners));
    }

    // Kontroll om arrayen behöver ökas i storlek och gör det isåfall
    private void checkOwnerCapacity() {
        if (ownerSize == ownerArray.length) {
            ownerArray = Arrays.copyOf(ownerArray, ownerSize * 2);
        }
    }
}