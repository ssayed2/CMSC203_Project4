package abc;


/*
 * Class: CMSC203
 * Instructor: Gray Thai
 * Description: Rental property with name, city, rent, owner, and a Plot.
 * Due: 11/03/2025
 * Platform/compiler:
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Samiullah
 */
public class Property {
    private String propertyName;
    private String city;
    private String owner;
    private double rentAmount;
    private Plot plot;

    // Constructors
    public Property() {
        this("", "", 0.0, "", 0, 0, 1, 1);
    }

    public Property(String propertyName, String city, double rentAmount, String owner) {
        this(propertyName, city, rentAmount, owner, 0, 0, 1, 1);
    }

    public Property(String propertyName, String city, double rentAmount, String owner,
                    int x, int y, int width, int depth) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }

    // Copy constructor
    public Property(Property other) {
        if (other == null) {
            this.propertyName = "";
            this.city = "";
            this.owner = "";
            this.rentAmount = 0.0;
            this.plot = new Plot();
        } else {
            this.propertyName = other.propertyName;
            this.city = other.city;
            this.owner = other.owner;
            this.rentAmount = other.rentAmount;
            this.plot = new Plot(other.plot);
        }
    }

    // Getters/Setters
    public String getPropertyName() { return propertyName; }
    public String getCity() { return city; }
    public String getOwner() { return owner; }
    public double getRentAmount() { return rentAmount; }
    public Plot getPlot() { return new Plot(plot); }

    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public void setCity(String city) { this.city = city; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setRentAmount(double rentAmount) { this.rentAmount = rentAmount; }
    public void setPlot(int x, int y, int width, int depth) { this.plot = new Plot(x, y, width, depth); }

    /** Format: propertyName,city,owner,rentAmount */
    @Override
    public String toString() {
        return propertyName + "," + city + "," + owner + "," + rentAmount;
    }
}
