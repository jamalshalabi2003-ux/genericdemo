

   package genericdemo;

import java.util.ArrayList;

// ---------- Generic class with ONE type parameter ----------
class Box<T> {

    protected T width, height;

    public Box(T width, T height) {
        set(width, height);
    }

    public void set(T width, T height) {
        this.width = width;
        this.height = height;
    }

    public T getWidth() {
        return width;
    }

    public T getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "[" + width.toString() + " x " + height.toString() + "]";
    }
}

// ---------- Generic subclass, T constrained to Number ----------
class ColoredBox<T extends Number> extends Box<T> {

    private String color;

    public ColoredBox(T width, T height, String color) {
        super(width, height);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public double area() {
        return width.doubleValue() * height.doubleValue();
    }

    @Override
    public String toString() {
        return super.toString() + " color=" + color;
    }
}

// ---------- Generic class with MULTIPLE type parameters ----------
class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

// ---------- Main class ----------
public class Genericdemo {

    // Wildcard without bound: needs a cast
    static double areaUnbounded(Box<?> b) {
        Number n1 = (Number) b.getWidth();
        Number n2 = (Number) b.getHeight();
        return n1.doubleValue() * n2.doubleValue();
    }

    // Wildcard with upper bound: no cast needed
    static double area(Box<? extends Number> b) {
        Number n1 = b.getWidth();
        Number n2 = b.getHeight();
        return n1.doubleValue() * n2.doubleValue();
    }

    // Generic method: same type T for parameters and return type
    static <T extends Number> Box<T> bigger(Box<T> a, Box<T> b) {
        double areaA = a.getWidth().doubleValue() * a.getHeight().doubleValue();
        double areaB = b.getWidth().doubleValue() * b.getHeight().doubleValue();
        return (areaA >= areaB) ? a : b;
    }

    public static void main(String[] args) {

        System.out.println("=== 1. Generic class Box<T> ===");
        Box<String> names = new Box<String>("Ali", "Omar");
        System.out.println(names);
        Box<Number> sizes = new Box<Number>(3.5, 2.0);
        System.out.println(sizes);

        System.out.println("\n=== 2. Autoboxing and Unboxing ===");
        Box<Integer> intBox = new Box<Integer>(4, 6);
        int w = intBox.getWidth();
        System.out.println("Unboxed width = " + w);

        System.out.println("\n=== 3. Raw type (needs casting) ===");
        @SuppressWarnings({"rawtypes", "unchecked"})
        Box raw = new Box("Table", 5);
        String label = (String) raw.getWidth();
        int number = (Integer) raw.getHeight();
        System.out.println(label + " , " + number);

        System.out.println("\n=== 4. Wildcards ===");
        Box<Integer> b1 = new Box<Integer>(3, 4);
        Box<Double> b2 = new Box<Double>(2.5, 4.0);
        System.out.println("Unbounded wildcard : " + areaUnbounded(b1));
        System.out.println("Bounded wildcard   : " + area(b2));

        System.out.println("\n=== 5. Generic method ===");
        Box<Integer> big = bigger(new Box<Integer>(2, 3), new Box<Integer>(5, 4));
        System.out.println("The bigger box is " + big);

        System.out.println("\n=== 6. Constrained class + inheritance ===");
        ColoredBox<Integer> cb = new ColoredBox<Integer>(3, 5, "Red");
        System.out.println(cb);
        System.out.println("Area = " + cb.area());

        System.out.println("\n=== 7. Multiple type parameters ===");
        Pair<String, Integer> student = new Pair<String, Integer>("Ahmed", 20);
        System.out.println(student.getKey() + " -> " + student.getValue());

        System.out.println("\n=== 8. ArrayList is a generic class ===");
        ArrayList<String> list = new ArrayList<String>();
        list.add("Java is fun");
        String s = list.get(0);
        System.out.println(s);
    }
}