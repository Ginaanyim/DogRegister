
//Virginia Anyim vian2657
import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {

    public int compare(Dog a, Dog b) {
        if (a.getTailLength() < b.getTailLength()) {
            return -1;
        } else if (a.getTailLength() > b.getTailLength()) {
            return 1;
        } else {
            return a.getName().compareTo(b.getName());
        }

    }

}
