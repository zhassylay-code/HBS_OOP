package Controller;

import repository.GuestRepository;

public class GuestController {

    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public void showProfile() {
        System.out.println("Guest profile (demo)");

    }
}
