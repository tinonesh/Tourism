import java.io.*;

public class TravelManager {
    private DynamicList<Trip> trips = new DynamicList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public DynamicList<Trip> getTrips() {
        return trips;
    }

    public void sortTripsByPrice() {
        for (int i = 0; i < trips.size(); i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if (trips.get(i).getPrice() > trips.get(j).getPrice()) {
                    Trip tmp = trips.get(i);
                    trips.set(i, trips.get(j));
                    trips.set(j, tmp);
                }
            }
        }
    }

    public void sortTripsByCountryCity() {
        for (int i = 0; i < trips.size(); i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                Trip a = trips.get(i);
                Trip b = trips.get(j);
                String ca = a.getDestination().getCountry() + a.getDestination().getCity();
                String cb = b.getDestination().getCountry() + b.getDestination().getCity();
                if (ca.compareTo(cb) > 0) {
                    trips.set(i, b);
                    trips.set(j, a);
                }
            }
        }
    }

    public void sortTripsByStartDate() {
        for (int i = 0; i < trips.size(); i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if (trips.get(i).getStartDate().isAfter(trips.get(j).getStartDate())) {
                    Trip tmp = trips.get(i);
                    trips.set(i, trips.get(j));
                    trips.set(j, tmp);
                }
            }
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < trips.size(); i++) {
                writer.write(trips.get(i).toFileString());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        trips.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Trip trip = Trip.fromFileString(line);
                if (trip != null) {
                    trips.add(trip);
                }
            }
        }
    }
}
