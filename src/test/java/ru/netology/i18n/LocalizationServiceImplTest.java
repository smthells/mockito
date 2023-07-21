package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceImplTest {

    @Test
    public void testLocaleOnRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        Country russia = Country.RUSSIA;
        String actual = localizationService.locale(russia);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(names = {"USA", "BRAZIL", "GERMANY"})
    void testLocaleOnOtherCountry(Country country) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        String actual = localizationService.locale(country);
        assertEquals(expected, actual);
    }
}