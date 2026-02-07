package repository;

import entity.Guest;

public interface GuestRepository {
    Guest findById(Long id);
    Guest findByDocument(String document);
    Guest save(Guest guest);

}
