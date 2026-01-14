
import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog> {

    public int compare(Dog a, Dog b) {
        int result = Double.compare(a.getTailLength(), b.getTailLength());
        if (result != 0) {
            return result;
        } else {
            return a.getName().compareToIgnoreCase(b.getName());
        }
    }
}
