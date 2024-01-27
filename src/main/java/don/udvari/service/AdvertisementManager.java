package don.udvari.service;

import don.udvari.model.Advertisement;

import java.util.logging.Logger;

public class AdvertisementManager {

    private static final Logger logger = Logger.getLogger(AdvertisementManager.class.getName());
    public AdvertisementManager(int advertisementPeriodLength) {
        this.advertisementSystem = new AdvertisementSystemImpl(advertisementPeriodLength);
    }

    private final AdvertisementSystem advertisementSystem;

    private int day = 1;

    public void stepDay() {
        day++;
        logger.info(String.format("New day is %d", day));
    }

    public void askNewAdvertisement() {
        logger.info(String.format("Asking new advertisement for day  %d", day));
        advertisementSystem.showNextAdvertisement(day);
    }

    public void registerNewAdvertisement(Advertisement advertisement) {
        logger.info(String.format("Registering new advertisement with name %s", advertisement.getTitle()));
        advertisementSystem.registerAdvertisement(advertisement);
    }

    public AdvertisementSystem getAdvertisementSystem() {
        return advertisementSystem;
    }

    public int getDay() {
        return day;
    }
}
