import java.util.ArrayList;
import java.util.Comparator;

public class Owner implements Comparable<Owner> {
    private ArrayList<Dog> dogList = new ArrayList<>();
    private String name;

    public Owner(String name) {
        this.name = normalizeString(name);
    }

    public String getName() {
        return name;
    }

    private String normalizeString(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        String dogNames = "";

        int i = 0;
        for (Dog dog : dogList) {
            if (i > 0) {
                dogNames += ", ";
            }
            dogNames += dog.getName();
            i++;
        }

        return name + " " + dogNames;
    }

    @Override
    public int compareTo(Owner other) {
        return this.name.compareTo(other.name);
    }

    public boolean addDog(Dog dog) {
        if (dog == null || dog.getOwner() != this || dogList.contains(dog)) {
            return false;
        }

        dogList.add(dog);
        return true;
    }

    public boolean removeDog(Dog dog) {
        if (dog == null) {
            return false;
        }

        return dogList.remove(dog);
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogListCopy = new ArrayList<>(dogList);
        Comparator<Dog> comparator = new DogNameComparator();
        DogSorter.sortDogs(comparator, dogListCopy);
        return dogListCopy;
    }
}