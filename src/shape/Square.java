package shape;

public final class Square implements Shape {
    private final int side;

    public Square(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("side doit être > 0");
        }
        this.side = side;
    }

    @Override
    public int area() { return side * side; }
}