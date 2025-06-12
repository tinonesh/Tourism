import java.time.LocalDate;
import java.util.Comparator;

public class TravelManager {
    private DynamicList<Trip> trips;

    public TravelManager() {
        trips = new DynamicList<>();
    }

    

    public void addTrip(Trip trip) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).toString().equals(trip.toString())) throw new IllegalArgumentException("Trip already exists");
        }
        trips.add(trip);
    }

    public DynamicList<Trip> getTrips() {
        return trips;
    }

    public void removeTrip(int index) {
        trips.remove(index);
    }

    public void sortTripsByCountryCity() {
        trips.sort(Comparator.comparing((Trip t) -> t.getDestination().getCountry() + t.getDestination().getCity()));
    }

    public void sortTripsByPrice() {
        trips.sort(Comparator.comparingDouble(Trip::getPrice));
    }

    public void sortTripsByStartDate() {
        trips.sort(Comparator.comparing(Trip::getStartDate));
    }
}