
import java.util.ArrayList;
import java.util.Comparator;

public class OwnerCollection {

    private ArrayList<Owner> owners;

    public OwnerCollection(ArrayList<Owner> owners) {
        this.owners = owners;
    }

    public OwnerCollection() {
        this.owners = new ArrayList<Owner>();
    }

    public boolean addOwner(Owner owner) {
        if (containsOwner(owner)) {
            return false;
        }
        owners.add(owner);
        return true;
    }

    public boolean removeOwner(String ownerName) {
        if (containsOwner(ownerName)) {
            owners.remove(getOwner(ownerName));
            return true;
        }
        return false;
    }

    public boolean removeOwner(Owner owner) {
        if (containsOwner(owner)) {
            owners.remove(owner);
            return true;
        }
        return false;
    }

    public boolean containsOwner(String ownerName) {
        for (Owner o : owners) {
            if (o.getName().equalsIgnoreCase(ownerName)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOwner(Owner owner) {
        for (Owner o : owners) {
            if (o.equals(owner)) {
                return true;
            }
        }
        return false;
    }

    public Owner getOwner(String ownerName) {
        for (Owner o : owners) {
            if (o.getName().equalsIgnoreCase(ownerName)) {
                return o;
            }
        }
        return null;
    }

    public ArrayList<Owner> getAllOwners() {
        ArrayList<Owner> copy = owners;
        copy.sort(Comparator.comparing(Owner::getName));
        return copy;
    }

    public int size() {
        return owners.size();
    }
}
