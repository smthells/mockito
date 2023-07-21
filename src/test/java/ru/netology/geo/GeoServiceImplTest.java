package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    public void testByMoscowIp() {
        GeoService geoService = new GeoServiceImpl();
        String moscowIp = GeoServiceImpl.MOSCOW_IP;
        Country country = Country.RUSSIA;
        String street = "Lenina";
        Country actualCountry = geoService.byIp(moscowIp).getCountry();
        String actualStreet = geoService.byIp(moscowIp).getStreet();
        assertEquals(country, actualCountry);
        assertEquals(street, actualStreet);
    }

    @Test
    public void testByRussiaIp() {
        GeoService geoService = new GeoServiceImpl();
        String russiaIp = "172.11.12.19";
        Country country = Country.RUSSIA;
        Country actualCountry = geoService.byIp(russiaIp).getCountry();
        assertEquals(country, actualCountry);
    }

    @Test
    public void testByNewYorkIp() {
        GeoService geoService = new GeoServiceImpl();
        String newYorkIpIp = GeoServiceImpl.NEW_YORK_IP;
        Country country = Country.USA;
        String street = "10th Avenue";
        Country actualCountry = geoService.byIp(newYorkIpIp).getCountry();
        String actualStreet = geoService.byIp(newYorkIpIp).getStreet();
        assertEquals(country, actualCountry);
        assertEquals(street, actualStreet);
    }

    @Test
    public void testByUsaIp() {
        GeoService geoService = new GeoServiceImpl();
        String usaIp = "96.11.186.141";
        Country country = Country.USA;
        Country actualCountry = geoService.byIp(usaIp).getCountry();
        assertEquals(country, actualCountry);
    }

    @Test
    public void testByIpOnNull() {
        GeoService geoService = new GeoServiceImpl();
        String anotherIp = "131.1.212.141";
        Location actualCountry = geoService.byIp(anotherIp);
        assertNull(actualCountry);
    }
}