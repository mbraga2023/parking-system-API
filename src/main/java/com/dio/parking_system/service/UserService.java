package com.dio.parking_system.service;

import com.dio.parking_system.domain.User;
import com.dio.parking_system.repository.CarRepo;
import com.dio.parking_system.repository.ParkingRepo;
import com.dio.parking_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CarRepo carRepository;

    @Autowired
    private ParkingRepo parkingHistoryRepository;

    @Transactional
    public User createUser(User user) {
        // Set relationships here if needed
        user.getCars().forEach(car -> {
            car.setUser(user);
            car.getHistory().forEach(history -> history.setCar(car));
        });
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all user entities.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user entity.
     * @return An Optional containing the updated user if successful, otherwise empty.
     */
    @Transactional
    public Optional<User> updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Ensure that the ID is set for the update operation.
            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty(); // User not found.
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return True if the user was deleted, otherwise false.
     */
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // Successfully deleted.
        } else {
            return false; // User not found.
        }
    }
}
