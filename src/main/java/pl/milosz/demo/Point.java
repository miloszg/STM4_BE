package pl.milosz.demo;

public class Point {
    private final String name;
    private double x;
    private double y;

    public Point(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this("", x, y);
    }

    public void limit(double minX, double minY, double maxX, double maxY) {
        if (getX() < minX) {
            setX(minX);
        }

        if (getY() < minY) {
            setY(minY);
        }

        if (getX() > maxX) {
            setX(maxX);
        }

        if (getY() > maxY) {
            setY(maxY);
        }

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"x\": " + String.valueOf(x) + ",\n" +
                "  \"y\": " + String.valueOf(y) + "\n" +
                "}\n";
    }
}
