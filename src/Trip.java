import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trip {
    private String name;
    private Destination destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int seats;

    public Trip(String name, Destination destination, LocalDate startDate, LocalDate endDate, double price, int seats) {
        this.name = name;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.seats = seats;
    }

    public String getName() { return name; }
    public Destination getDestination() { return destination; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getPrice() { return price; }
    public int getSeats() { return seats; }

    public String toFileString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return name + "|" + destination.getCountry() + "|" + destination.getCity() + "|" + destination.getDescription()
            + "|" + startDate.format(fmt) + "|" + endDate.format(fmt) + "|" + price + "|" + seats;
    }

    public static Trip fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new Trip(
                parts[0],
                new Destination(parts[1], parts[2], parts[3]),
                LocalDate.parse(parts[4], fmt),
                LocalDate.parse(parts[5], fmt),
                Double.parseDouble(parts[6]),
                Integer.parseInt(parts[7])
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return name + " | " + destination + " | " + startDate + " - " + endDate + " | $" + price + " | Seats: " + seats;
    }
}
