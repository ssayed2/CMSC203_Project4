package abc;

/*
 * Class: CMSC203
 * Instructor: Gray Thai
 * Description: Rectangle-like plot with top-left (x,y), width, depth; supports overlaps/encompass checks.
 * Due: 11/03/2025
 * Platform/compiler:
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Samiullah
 */
public class Plot {
    private int x;      // left
    private int y;      // top
    private int width;  // horizontal extent
    private int depth;  // vertical extent

    // Constructors
    public Plot() {
        this(0, 0, 1, 1);
    }

    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    // Copy constructor
    public Plot(Plot other) {
        if (other == null) {
            this.x = 0; this.y = 0; this.width = 1; this.depth = 1;
        } else {
            this.x = other.x;
            this.y = other.y;
            this.width = other.width;
            this.depth = other.depth;
        }
    }

    // Getters/Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getDepth() { return depth; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setDepth(int depth) { this.depth = depth; }

    /**
     * True if the current plot has area overlap with p.
     * Touching edges/corners ONLY do NOT count as overlap (per spec examples).
     */
    public boolean overlaps(Plot p) {
        if (p == null) return false;
        boolean separated =
                (this.x + this.width) <= p.x ||
                (p.x + p.width) <= this.x ||
                (this.y + this.depth) <= p.y ||
                (p.y + p.depth) <= this.y;
        return !separated;
    }

    /**
     * True if current plot fully contains p (edges allowed).
     */
    public boolean encompasses(Plot p) {
        if (p == null) return false;
        return p.x >= this.x &&
               p.y >= this.y &&
               (p.x + p.width) <= (this.x + this.width) &&
               (p.y + p.depth) <= (this.y + this.depth);
    }

    /** Format: x,y,width,depth */
    @Override
    public String toString() {
        return x + "," + y + "," + width + "," + depth;
    }
}
