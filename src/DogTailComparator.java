
//Virginia Anyim vian2657
import java.util.Comparator;

public class DogTailComparator implements Comparator<Dog> {

    public int compare(Dog a, Dog b) {
        if (a.getTailLength() < b.getTailLength())
            return -1;
        if (a.getTailLength() > b.getTailLength())
            return 1;

        return 0;
    }
}
