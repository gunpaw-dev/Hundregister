
public class Dog {

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = formatString(name);
        this.breed = formatString(breed);
        this.age = formatInt(age);
        this.weight = formatInt(weight);
        if (this.breed.equals("Tax") || this.breed.equals("Dachshund")) {
            tailLength = 3.7;
        } else {
            updateTailLength();
        }
    }

    public Dog(String name, String breed, int age, int weight, Owner owner) {
        this.name = formatString(name);
        this.breed = formatString(breed);
        this.age = formatInt(age);
        this.weight = formatInt(weight);
        if (this.breed.equals("Tax") || this.breed.equals("Dachshund")) {
            tailLength = 3.7;
        } else {
            updateTailLength();
        }
        setOwner(owner);
    }

    public String getName() {
        return name;
    }

    private String formatString(String input) {
        if (input != null && !input.isEmpty() && !input.equals("")) {
            return input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        } else {
            throw new IllegalArgumentException(input + "är inte ett IllegalArgumentException eller ett NullPointerException");
        }

    }

    private int formatInt(int input) {
        if (input < 0) {
            throw new IllegalArgumentException(input + "är ett negativt nummer");
        } else {
            return input;
        }
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public void updateAge(int newAge) {
        if (newAge > age) {
            age = newAge;
        }
    }

    public int getWeight() {
        return weight;
    }

    private void updateTailLength() {
        tailLength = (double) (age * weight) / 10;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean setOwner(Owner owner) {
        boolean result = false;
        return result;
    }

    public boolean setOwner() {
        boolean result = false;
        return result;
    }
}
