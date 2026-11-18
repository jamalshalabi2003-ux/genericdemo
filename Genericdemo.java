// 1. Custom Checked Exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// 2. WorkshopRegistration Class
class WorkshopRegistration {
    public void registerParticipant(String name, int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException(
                "Registration denied for " + name +
                ": Must be at least 18 years old (provided age: " + age + ")"
            );
        }
        System.out.println("Success: " + name + " has been registered for the workshop.");
    }
}

// 3. Main Class
public class Main {
    public static void main(String[] args) {
        WorkshopRegistration workshop = new WorkshopRegistration();

        try {
            workshop.registerParticipant("Ahmed", 16);
        } catch (InvalidAgeException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}