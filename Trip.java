import java.time.LocalDate;

public class Trip {
    private String name;
    private Destination destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int availableSeats;
    private boolean isSpecial;

    public Trip(String name, Destination destination, LocalDate startDate, LocalDate endDate, double price, int availableSeats, boolean isSpecial) {
        if (endDate.isBefore(startDate)) throw new IllegalArgumentException("End date must be after start date.");
        this.name = name;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.availableSeats = availableSeats;
        this.isSpecial = isSpecial;
    }

    public Trip(String name, Destination destination, LocalDate startDate, LocalDate endDate, double price, int availableSeats) {
        this(name, destination, startDate, endDate, price, availableSeats, false);
    }

    public String getName() { return name; }
    public Destination getDestination() { return destination; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }
    public boolean isSpecial() { return isSpecial; }

    public void reserveSeat() {
        if (availableSeats <= 0) throw new IllegalStateException("No seats left");
        availableSeats--;
    }

    @Override
    public String toString() {
        return (isSpecial ? "[Special Offer] " : "[Regular] ") + name + " | " + destination + " | " + startDate + " - " + endDate + " | $" + price + " | Seats: " + availableSeats;
    }
}
