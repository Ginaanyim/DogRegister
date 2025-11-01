import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {

    public int compare(Dog a, Dog b) {
        return a.getName().compareTo(b.getName());
    }

}
