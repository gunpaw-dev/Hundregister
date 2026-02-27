
import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    private DogSorter() {
    }

    ;
    public static void sort(SortingAlgorithm algorithm, Comparator<Dog> comparator, Dog... dogs) {

        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(comparator, dogs);
                break;
            case SELECTION_SORT:
                selectionSort(comparator, dogs);
                break;
        }

    }

    public static void sort(SortingAlgorithm algorithm, Comparator<Dog> comparator, ArrayList<Dog> dogs) {

        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(comparator, dogs);
                break;
            case SELECTION_SORT:
                selectionSort(comparator, dogs);
                break;
        }

    }

    private static void bubbleSort(Comparator<Dog> comparator, Dog... dogs) {
        int sorted = dogs.length;
        boolean changed;
        do {
            changed = false;
            for (int i = 1; i < sorted; i++) {
                if (comparator.compare(dogs[i], dogs[i - 1]) < 0) {
                    Dog temp = dogs[i];
                    dogs[i] = dogs[i - 1];
                    dogs[i - 1] = temp;
                    changed = true;
                }
            }
            sorted--;
        } while (changed);
    }

    private static void bubbleSort(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        int sorted = dogs.size();
        boolean changed;
        do {
            changed = false;
            for (int i = 1; i < sorted; i++) {
                if (comparator.compare(dogs.get(i), dogs.get(i - 1)) < 0) {
                    Dog temp = dogs.get(i);
                    dogs.set(i, dogs.get(i - 1));
                    dogs.set(i - 1, temp);
                    changed = true;
                }
            }
            sorted--;
        } while (changed);
    }

    private static void selectionSort(Comparator<Dog> comparator, Dog... dogs) {
        int currentMin;
        int currentItem;
        for (int i = 0; i < dogs.length; i++) {
            for (currentItem = i, currentMin = i; currentItem < dogs.length; currentItem++) {
                if (comparator.compare(dogs[currentMin], dogs[currentItem]) > 0) {
                    currentMin = currentItem;
                }
            }
            Dog temp = dogs[i];
            dogs[i] = dogs[currentMin];
            dogs[currentMin] = temp;
        }
    }

    private static void selectionSort(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        int currentMin;
        int currentItem;
        for (int i = 0; i < dogs.size(); i++) {
            for (currentItem = i, currentMin = i; currentItem < dogs.size(); currentItem++) {
                if (comparator.compare(dogs.get(currentMin), dogs.get(currentItem)) > 0) {
                    currentMin = currentItem;
                }
            }
            Dog temp = dogs.get(i);
            dogs.set(i, dogs.get(currentMin));
            dogs.set(currentMin, temp);
        }
    }

}
