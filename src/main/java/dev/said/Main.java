package dev.said;

import dev.said.entity.Airplane;
import dev.said.service.BookingService;
import dev.said.util.FileUtils;

public class Main {
    public static void main(String[] args) {
        Airplane airplane = FileUtils.loadAirplane();
        BookingService bookingService = new BookingService(airplane);
        bookingService.interactiveMenu();
        // TODO: не выходят места, нужно проверить причину )
    }
}
