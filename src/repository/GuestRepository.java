package repository;

import entity.Guest;

public interface GuestRepository {
    Guest findById(Long id);
}
