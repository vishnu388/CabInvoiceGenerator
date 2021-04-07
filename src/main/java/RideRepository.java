import java.util.HashMap;

public class RideRepository {
    private final HashMap<Integer, Ride[]> rideRepository;
    private final Integer id;

    public RideRepository(HashMap<Integer, Ride[]> rideRepository, Integer id) {
        this.rideRepository = rideRepository;
        this.id = id;
    }

    public InvoiceSummary calculateFare() {
        try {
            if (this.id == null || this.rideRepository.get(id) == null) {
                throw new NullPointerException("Null value not allowed.");
            }
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            Ride[] userRideDetails = this.rideRepository.get(this.id);
            double totalFare = 0;
            for (Ride ride : userRideDetails) {
                totalFare += invoiceGenerator.calculateFare(ride.distance, ride.time);
            }
            return new InvoiceSummary(userRideDetails.length, totalFare);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
