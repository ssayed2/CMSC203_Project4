package abc;

/** Class: CMSC203
 * Instructor: Gray Thai
 * Description: Manages up to MAX_PROPERTY properties inside a 10x10 company plot.
 * Due: MM/DD/YYYY
 * Platform/compiler:
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Samiullah
 */
public class ManagementCompany {
    // Constants
    public static final int MAX_PROPERTY = 5;
    public static final int MGMT_WIDTH  = 10;
    public static final int MGMT_DEPTH  = 10;

    // Attributes
    private String name;
    private String taxID;
    private double mgmtFeePercent; // 0..100
    private Property[] properties;
    private Plot plot;             // company plot
    private int numberOfProperties;

    // Constructors
    public ManagementCompany() {
        this("", "", 0.0, 0, 0, MGMT_WIDTH, MGMT_DEPTH);
    }

    public ManagementCompany(String name, String taxID, double mgmtFeePercent) {
        this(name, taxID, mgmtFeePercent, 0, 0, MGMT_WIDTH, MGMT_DEPTH);
    }

    public ManagementCompany(String name, String taxID, double mgmtFeePercent,
                             int x, int y, int width, int depth) {
        this.name = name;
        this.taxID = taxID;
        this.mgmtFeePercent = mgmtFeePercent;
        this.plot = new Plot(x, y, width, depth);
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
    }

    // Copy constructor
    public ManagementCompany(ManagementCompany other) {
        if (other == null) {
            this.name = "";
            this.taxID = "";
            this.mgmtFeePercent = 0.0;
            this.plot = new Plot(0,0,MGMT_WIDTH,MGMT_DEPTH);
            this.properties = new Property[MAX_PROPERTY];
            this.numberOfProperties = 0;
        } else {
            this.name = other.name;
            this.taxID = other.taxID;
            this.mgmtFeePercent = other.mgmtFeePercent;
            this.plot = new Plot(other.plot);
            this.properties = new Property[MAX_PROPERTY];
            for (int i = 0; i < MAX_PROPERTY; i++) {
                if (other.properties[i] != null) {
                    this.properties[i] = new Property(other.properties[i]);
                }
            }
            this.numberOfProperties = other.numberOfProperties;
        }
    }

    // Getters/Setters
    public String getName() { return name; }
    public String getTaxID() { return taxID; }
    public double getMgmFeePer() { return mgmtFeePercent; } // legacy accessor some tests use
    public double getManagementFeePercent() { return mgmtFeePercent; }
    public Plot getPlot() { return new Plot(plot); }
    public Property[] getProperties() { return properties; }

    public void setName(String name) { this.name = name; }
    public void setTaxID(String taxID) { this.taxID = taxID; }
    public void setManagementFeePercent(double mgmtFeePercent) { this.mgmtFeePercent = mgmtFeePercent; }
    public void setPlot(int x, int y, int width, int depth) { this.plot = new Plot(x, y, width, depth); }

    // Status helpers
    public boolean isPropertiesFull() { return numberOfProperties >= MAX_PROPERTY; }
    public int getPropertiesCount() { return numberOfProperties; }

    // Spelling varies across handouts; include both for safety
    public boolean isMangementFeeValid() { return isManagementFeeValid(); }
    public boolean isManagementFeeValid() {
        return mgmtFeePercent >= 0 && mgmtFeePercent <= 100;
    }

    // ----- addProperty overloads -----

    /**
     * Return codes:
     * -1: array full
     * -2: property null
     * -3: property plot not encompassed by company plot
     * -4: property plot overlaps an existing property
     * >=0: index where added
     */
    public int addProperty(Property property) {
        if (isPropertiesFull()) return -1;
        if (property == null) return -2;

        Plot pPlot = property.getPlot();
        if (!plot.encompasses(pPlot)) return -3;

        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null && properties[i].getPlot().overlaps(pPlot)) {
                return -4;
            }
        }

        int idx = numberOfProperties;          // append at end
        properties[idx] = new Property(property); // defensive copy
        numberOfProperties++;
        return idx;
    }

    public int addProperty(String name, String city, double rent, String owner) {
        return addProperty(new Property(name, city, rent, owner));
    }

    public int addProperty(String name, String city, double rent, String owner,
                           int x, int y, int width, int depth) {
        return addProperty(new Property(name, city, rent, owner, x, y, width, depth));
    }

    // Totals & queries
    public double getTotalRent() {
        double sum = 0.0;
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null) sum += properties[i].getRentAmount();
        }
        return sum;
    }

    // Correct spelling (internal)
    private Property getHighestRentPropertyInternal() {
        if (numberOfProperties == 0) return null;
        int maxIdx = -1;
        double maxRent = -1;
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null && properties[i].getRentAmount() > maxRent) {
                maxRent = properties[i].getRentAmount();
                maxIdx = i;
            }
        }
        return (maxIdx >= 0) ? new Property(properties[maxIdx]) : null;
    }

    // GUI calls this misspelled name; keep it for compatibility.
    public Property getHighestRentPropperty() {
        return getHighestRentPropertyInternal();
    }

    public void removeLastProperty() {
        if (numberOfProperties <= 0) return;
        properties[numberOfProperties - 1] = null;
        numberOfProperties--;
    }

    private double calcTotalManagementFee() {
        return getTotalRent() * (mgmtFeePercent / 100.0);
    }

    /**
     * Must match GFA expected format EXACTLY, including the blank line
     * and no trailing newline at the very end.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of the properties for ").append(name)
          .append(", taxID: ").append(taxID).append("\n");
        sb.append("______________________________________________________\n");
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i] != null) {
                sb.append(properties[i].toString()).append("\n");
            }
        }
        sb.append("______________________________________________________\n")
          .append("\n")
          .append(" total management Fee: ")
          .append(String.format("%.2f", calcTotalManagementFee()));
        return sb.toString(); // NO trailing newline
    }
}
