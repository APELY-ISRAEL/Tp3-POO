package shape;

public final class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width et height doivent être > 0");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public int area() { return width * height; }
}