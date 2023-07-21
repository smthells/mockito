package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {
    @Mock
    private GeoServiceImpl geoService;
    @Mock
    private LocalizationServiceImpl localizationService;
    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    public void testSendRussianText() {
        String expected = "Добро пожаловать";
        String russiaIp = "172.111.11.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russiaIp);
        Location russiaLocation = new Location("Moscow", Country.RUSSIA, "Prostornay", 15);
        when(geoService.byIp(russiaIp)).thenReturn(russiaLocation);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String actual = messageSender.send(headers);
        verify(localizationService, times(2)).locale(Country.RUSSIA);
        assertEquals(expected, actual);
        assertEquals("Отправлено сообщение: Добро пожаловать", outputStream.toString());
    }

    @Test
    public void testSendEnglishText() {
        String expected = "Welcome";
        String usaIp = "96.31.199.149";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, usaIp);
        Location usaLocation = new Location("New York", Country.USA, "12th Avenue", 32);
        when(geoService.byIp(usaIp)).thenReturn(usaLocation);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String actual = messageSender.send(headers);
        verify(localizationService, times(2)).locale(Country.USA);
        assertEquals(expected, actual);
        assertEquals("Отправлено сообщение: Welcome", outputStream.toString());
    }
}