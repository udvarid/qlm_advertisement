package don.udvari.model;


import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

public class AdvertisementImpl implements Advertisement{

    private static final Logger logger = Logger.getLogger(AdvertisementImpl.class.getName());

    public AdvertisementImpl(int maxAppearance, double weight, String title) {
        if (maxAppearance <= 0) {
            throw new IllegalArgumentException("Max appearance must be a positive integer");
        }
        if (weight <= 0 || weight > 1) {
            throw new IllegalArgumentException("Weight must be a value between 0 and 1");
        }
        if (title.isBlank() || title.isEmpty()) {
            throw new IllegalArgumentException("We need a non-empty title!");
        }
        this.maxAppearance = maxAppearance;
        this.weight = weight;
        this.title = title;
        logger.info(String.format("Advertisement is created with name %s", title));
    }

    private final int maxAppearance;
    private final double weight;

    private final String title;
    private final Hashtable<Integer, Integer> allAppearances = new Hashtable<>();
    @Override
    public int getMaxAppearance() {
        return this.maxAppearance;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }


    @Override
    public int lastAppearence(int dayIndex, int numberOfDays) {
        if (dayIndex <= 0) {
            throw new IllegalArgumentException("Dayindex must be a positive integer");
        }
        if (numberOfDays <= 0) {
            throw new IllegalArgumentException("NumberOfDays must be a positive integer");
        }
        return allAppearances.entrySet().stream()
                .filter(entry -> entry.getKey() <= dayIndex)
                .filter(entry -> entry.getKey() > dayIndex - numberOfDays)
                .map(Map.Entry::getValue)
                .reduce(0, Integer::sum);
    }

    @Override
    public void showAdvertisement(int dayIndex) {
        if (dayIndex <= 0) {
            throw new IllegalArgumentException("Dayindex must be a positive integer");
        }
        logger.info(String.format("Showing advertisement of %s for day %d", this.title, dayIndex));
        allAppearances.merge(dayIndex, 1, Integer::sum);
    }

    @Override
    public Hashtable<Integer, Integer> getAllAppearances() {
        return this.allAppearances;
    }

    public String getTitle() {
        return title;
    }
}
