
import java.util.Comparator;
import java.util.Objects;

public class Owner {

    private String name;
    private Dog[] dogs;
    private int dogIndex;
    private int dogMax;

    public Owner(String name, Dog... dogs) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.dogs = new Dog[7];
        dogIndex = 0;
        if (dogs.length != 0) {
            for (Dog d : dogs) {
                addDog(d);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Dog[] getDogs() {
        if (!ownsAnyDog()) {
            return new Dog[0];
        } else {
            int copySize = 0;
            for (Dog d : dogs) {
                if (d != null) {
                    copySize++;
                }
            }
            Dog[] copy = new Dog[copySize];
            int copyIndex = 0;
            for (Dog d : dogs) {
                if (d != null) {
                    copy[copyIndex] = d;
                    copyIndex++;
                }
            }
            DogSorter.sort(SortingAlgorithm.BUBBLE_SORT, Comparator.comparing(Dog::getName), copy);
            return copy;
        }
    }

    public boolean addDog(Dog dog) {
        if (!ownsDog(dog.getName())) {
            int index = 0;
            for (Dog d : dogs) {
                if (d == null) {
                    dogs[index] = dog;
                    return true;
                }
                index++;
            }
            return false;
        }
        return false;
    }

    public boolean removeDog(String name) {
        int i = 0;
        for (Dog d : dogs) {
            if (d != null) {
                // if (Objects.equals(d.getName(), name)) {
                if (d.getName().equalsIgnoreCase(name)) {
                    dogs[i] = null;
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public boolean removeDog(Dog dog) {
        int i = 0;
        for (Dog d : dogs) {
            if (Objects.equals(d, dog)) {
                dogs[i] = null;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean ownsAnyDog() {
        for (Dog d : dogs) {
            if (d != null) {
                return true;
            }
        }
        return false;
    }

    public boolean ownsMaxDogs() {
        for (Dog d : dogs) {
            if (d == null) {
                return false;
            }
        }
        return true;

    }

    public boolean ownsDog(String dog) {
        for (Dog d : dogs) {
            if (d != null) {
                if (d.getName().equalsIgnoreCase(dog)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ownsDog(Dog dog) {
        for (Dog d : dogs) {
            if (Objects.equals(dog, d)) {
                return true;
            }
        }
        return false;
    }

    // private String formatString(String input) {
    //     if (input != null && !input.isEmpty() && !input.equals("")) {
    //         return input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    //     } else {
    //         throw new IllegalArgumentException(input + "är inte ett IllegalArgumentException eller ett NullPointerException");
    //     }
    // }
    @Override
    public String toString() {
        return "Owner: " + name;
    }
}
