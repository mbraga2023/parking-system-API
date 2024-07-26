package com.dio.parking_system.service;

import com.dio.parking_system.domain.Car;
import com.dio.parking_system.domain.ParkingHistory;
import com.dio.parking_system.domain.User;
import com.dio.parking_system.domain.dto.UserDTO;
import com.dio.parking_system.repository.CarRepo;
import com.dio.parking_system.repository.ParkingRepo;
import com.dio.parking_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.parse;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CarRepo carRepository;

    @Autowired
    private ParkingRepo parkingHistoryRepository;

    @Transactional
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        userDTO.getCars().forEach(carDTO -> {
            Car car = new Car();
            car.setManufacturer(carDTO.getManufacturer());
            car.setModel(carDTO.getModel());
            car.setColor(carDTO.getColor());
            car.setYear(carDTO.getYear());
            car.setPlate(carDTO.getPlate());
            car.setType(carDTO.getType());
            car.setUser(user);

            carDTO.getHistory().forEach(historyDTO -> {
                ParkingHistory history = new ParkingHistory();
                history.setCheckin(LocalDateTime.parse(historyDTO.getCheckin()));
                history.setCheckout(LocalDateTime.parse(historyDTO.getCheckout()));
                history.setCar(car);
                car.getHistory().add(history);
            });

            user.getCars().add(car);
        });

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> updateUser(Long id, UserDTO userDTO) {
        if (userRepository.existsById(id)) {
            User user = new User();
            user.setId(id);
            user.setName(userDTO.getName());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());

            // Handle cars and parking history
            // Clear existing cars and parking histories and set new ones from userDTO

            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
