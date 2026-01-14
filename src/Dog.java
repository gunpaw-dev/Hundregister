
public class Dog {

    private String name;
    private String breed;
    private int age;
    private int weight;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = formatString(name);
        this.breed = formatString(breed);
        this.age = formatInt(age);
        this.weight = formatInt(weight);
    }

    public Dog(String name, String breed, int age, int weight, Owner owner) {
        this.name = formatString(name);
        this.breed = formatString(breed);
        this.age = formatInt(age);
        this.weight = formatInt(weight);
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

    public void updateAge(int addedAge) {
        if (addedAge == Integer.MAX_VALUE || ((long) age + (long) addedAge) >= Integer.MAX_VALUE) {
            age = Integer.MAX_VALUE;
            return;
        }
        if (addedAge > 0) {
            age = age + addedAge;
        }
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        if (this.breed.equals("Tax") || this.breed.equals("Dachshund")) {
            return 3.7;
        } else {
            return (double) (age * weight) / 10;
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean setOwner(Owner owner) {
        if (owner == null) {
            this.owner.removeDog(this);
            this.owner = null;
            return true;
        }
        if (owner.ownsMaxDogs()) {
            return false;
        }
        if (this.owner == null) {
            this.owner = owner;
            owner.addDog(this);
            return true;
        } else if (!this.owner.equals(owner)) {
            this.owner.removeDog(this);
            this.owner = owner;
            owner.addDog(this);
            return true;
        } else if (owner.ownsDog(this)) {
            return false;
        } else {
            owner.addDog(this);
            return true;
        }
    }

    public boolean setOwner() {
        if (owner != null) {
            owner = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String dogInfo = "";
        if (this.getOwner() != null) {
            dogInfo
                    += "Owner: " + getOwner().getName();
        }
        dogInfo
                += "Namn: " + getName()
                + " Ras: " + getBreed()
                + " Ålder: " + getAge()
                + " Vikt (Kg): " + getWeight()
                + " Svanslängd: " + getTailLength();
        return dogInfo;
    }
}
