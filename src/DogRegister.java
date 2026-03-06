
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DogRegister {

    private static final String EXIT_COMMAND = "EXIT";
    private InputReader input = new InputReader(new Scanner(System.in));
    private OwnerCollection collection = new OwnerCollection();

    public static void main(String[] args) {
        DogRegister dr = new DogRegister();
        dr.startProgram();
    }

    private void startProgram() {
        println("--------------------------------Welcome to THE dog register!-------------------------------");
        showCommands();
        commandLoop();
    }

    private void showCommands() {
        println(" - Add owner      (AO)");
        println(" - Remove owner   (RO)");
        println(" - Add dog        (AD)");
        println(" - Remove dog     (RD)");
        println(" - Change owner   (CO)");
        println(" - List owners    (LO)");
        println(" - List dogs      (LD)");
        println(" - Increase age   (IA)");
        println(" - Get commands   (GA)");
        println(" - Exit");
    }

    private void commandLoop() {
        String command = "";
        do {
            command = input.readString("Enter command");
            checkForCommand(command);
        } while (!command.equals(EXIT_COMMAND));
        System.out.println("Dog Register has been shut down.");

    }

    private void addOwner() {
        String ownerName = formatString(input.readString("Enter the name of the new owner"));
        if (!collection.containsOwner(ownerName)) {
            collection.addOwner(new Owner(ownerName));
            println("The owner " + ownerName + " has successfully been added to the owner database.");
        } else {
            println("The owner " + ownerName + " was unable to be added to the owner database. The cause may be duplicate names.");
        }
    }

    private void removeOwner() {
        String ownerName = formatString(input.readString("Enter the name of the owner you want to remove"));
        if (collection.size() <= 0) {
            println("There are no current registered owners");
            return;
        }
        if (collection.containsOwner(ownerName)) {
            collection.removeOwner(ownerName);
            println("The owner " + ownerName + " has successfully and painlessly been removed from our database.");
        } else {
            println("The owner " + ownerName + " was unable to be found within our database.");
        }
    }

    private void addDog() {
        if (collection.size() > 0) {
            String ownerName = input.readString("Enter the name of the dogs owner");
            if (collection.getOwner(ownerName) != null && !collection.getOwner(ownerName).ownsMaxDogs()) {
                String dogName = "";
                collection.getOwner(ownerName).addDog(new Dog(
                        dogName = formatString(input.readString("What is the dog named")),
                        input.readString("What breed is the dog"),
                        input.readInt("How old is the dog"),
                        input.readInt("How much does the dog weight")
                ));
                println("The dog " + dogName + " was added to the owner " + formatString(ownerName) + ".");
            } else {
                println("The owner does not exist or has the maximum amount of dogs.");
            }

        } else {
            println("There are no owners within our database.");
        }
    }

    private void removeDog() {
        if (collectionHasDog()) {
            String ownerName = input.readString("Enter the name of the dogs owner");
            if (collection.getOwner(ownerName) != null) {
                String dogName = input.readString("Enter the name of the dog you want to remove from " + ownerName);
                if (collection.getOwner(ownerName).getDog(dogName) != null) {
                    collection.getOwner(ownerName).removeDog(dogName);
                    println("The dog " + formatString(dogName) + " was removed from " + formatString(ownerName) + ".");
                } else {
                    println("The dog " + formatString(dogName) + " could not be found as a dog of " + formatString(ownerName) + ".");
                }
            } else {
                println("Could not find " + ownerName + " within our database.");
            }

        } else {
            println("There are no dogs within our database.");
        }
    }

    private void changeOwner() {
        if (collection.size() > 1 && collectionHasDog()) {
            String oldOwnerName = input.readString("Enter the name of the dogs owner");
            if (collection.getOwner(oldOwnerName) != null) {
                if (collection.getOwner(oldOwnerName).ownsAnyDog()) {
                    String dogName = input.readString("Enter the name of the dog you want to change from " + oldOwnerName + " to a new owner");
                    if (collection.getOwner(oldOwnerName).ownsDog(dogName)) {
                        String newOwnerName = input.readString("Enter the name of the dogs new owner");
                        if (collection.getOwner(newOwnerName) != null && !collection.getOwner(newOwnerName).ownsMaxDogs() && collection.getOwner(newOwnerName) != collection.getOwner(oldOwnerName)) {
                            collection.getOwner(newOwnerName).addDog(collection.getOwner(oldOwnerName).getDog(dogName));
                            println("The dog " + formatString(dogName) + " was changed from " + oldOwnerName + " to " + formatString(newOwnerName) + ".");
                        }
                    } else {
                        println(dogName + " is not registered to " + oldOwnerName + ".");
                    }
                } else {
                    println("Owner did not have any dogs registered to them.");
                }
            } else {
                println("Owner was not found within our database.");
            }
        } else {
            println("There are not enough owners and/or dogs within our database.");
        }
    }

    private void listOwners() {
        if (collection.size() > 0) {
            for (Owner owner : collection.getAllOwners()) {
                println(owner.getName() + ", Dogs: ");
                for (Dog dog : owner.getDogs()) {
                    boolean first = true;
                    if (dog != null) {
                        if (first) {
                            print(dog.getName());
                            first = false;
                        } else {
                            print(", " + dog.getName());
                        }
                    }
                }
            }
        } else {
            println("There are no owners.");
        }
    }

    private void listDogs() {
        double minTailLegth = input.readInt("What is the minimum tail length of the dogs you want listed?");
        boolean dogFound = false;
        ArrayList<Dog> tempDogs = new ArrayList<>();
        if (collectionHasDog()) {
            for (Owner owner : collection.getAllOwners()) {
                for (Dog dog : owner.getDogs()) {
                    if (dog != null) {
                        if (dog.getTailLength() >= minTailLegth) {
                            tempDogs.add(dog);
                            dogFound = true;
                        }
                    }
                }
            }
            if (!dogFound) {
                println("No dogs over that tail length are registered.");
            } else {
                Dog[] sortedDogs = new Dog[tempDogs.size()];
                sortedDogs = tempDogs.toArray(sortedDogs);
                DogSorter.sort(SortingAlgorithm.SELECTION_SORT, Comparator.comparing(Dog::getTailLength), sortedDogs);
                for (Dog dog : tempDogs) {
                    println(
                            "Name: " + dog.getName()
                            + ", Breed: " + dog.getBreed()
                            + ", Age: " + dog.getAge()
                            + ", Weight: " + dog.getWeight()
                            + ", Tail Length: " + dog.getTailLength());
                }
            }
        } else {
            println("There are no dogs.");
        }
    }

    private void increaseAge() {
        if (collectionHasDog()) {
            for (Owner owner : collection.getAllOwners()) {
                for (Dog dog : owner.getDogs()) {
                    if (dog != null) {
                        dog.updateAge(1);
                    }
                }
            }
        } else {
            println("No dogs are registered.");
        }
    }

    private void checkForCommand(String command) {
        command = command.toUpperCase().trim().replace(" ", "_");
        switch (command) {
            case "ADD_OWNER", "AO":
                addOwner();
                break;
            case "REMOVE_OWNER", "RO":
                removeOwner();
                break;
            case "ADD_DOG", "AD":
                addDog();
                break;
            case "REMOVE_DOG", "RD":
                removeDog();
                break;
            case "CHANGE_OWNER", "CO":
                changeOwner();
                break;
            case "LIST_OWNERS", "LO":
                listOwners();
                break;
            case "LIST_DOGS", "LD":
                listDogs();
                break;
            case "INCREASE_AGE", "IA":
                increaseAge();
                break;
            case "EXIT":
                // No code because this just exits the program, just exists a case since there is an enum for it type shit.
                break;
            default:
                System.out.println("Command not recognized.");
        }
    }

    private boolean collectionHasDog() {
        for (Owner owner : collection.getAllOwners()) {
            if (owner.ownsAnyDog()) {
                return true;
            }
        }
        return false;
    }

    private void print(String string) {
        System.out.print(string);
    }

    private void println(String string) {
        System.out.println(string);
    }

    private void printList(ArrayList<String> strings) {
        Collections.sort(strings);
        String output = "";
        boolean first = true;
        for (String s : strings) {
            if (first) {
                output += s;
                first = false;

            } else {
                output += ", " + s;
            }
        }
        println(output);
    }

    private String formatString(String input) {
        if (input != null && !input.isEmpty()) {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        } else {
            throw new IllegalArgumentException(input + " är ett IllegalArgumentException eller ett NullPointerException");
        }
    }
}
