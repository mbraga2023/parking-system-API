package com.dio.parking_system.controller;

import com.dio.parking_system.domain.Car;
import com.dio.parking_system.domain.ParkingHistory;
import com.dio.parking_system.domain.User;
import com.dio.parking_system.domain.dto.CarDTO;
import com.dio.parking_system.domain.dto.ParkingHistoryDTO;
import com.dio.parking_system.domain.dto.UserDTO;
import com.dio.parking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        UserDTO response = mapToDTO(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(this::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(this::mapToDTO)
                .toList();
        return ResponseEntity.ok(userDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<User> updatedUser = userService.updateUser(id, userDTO);
        return updatedUser.map(this::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setCars(user.getCars().stream()
                .map(this::mapCarToDTO)
                .toList());
        return userDTO;
    }

    private CarDTO mapCarToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setManufacturer(car.getManufacturer());
        carDTO.setModel(car.getModel());
        carDTO.setColor(car.getColor());
        carDTO.setYear(car.getYear());
        carDTO.setPlate(car.getPlate());
        carDTO.setType(car.getType());
        carDTO.setHistory(car.getHistory().stream()
                .map(this::mapParkingHistoryToDTO)
                .toList());
        return carDTO;
    }

    private ParkingHistoryDTO mapParkingHistoryToDTO(ParkingHistory history) {
        ParkingHistoryDTO dto = new ParkingHistoryDTO();
        dto.setId(history.getId());
        dto.setCheckin(history.getCheckin().toString());
        dto.setCheckout(history.getCheckout().toString()); // Convert LocalDateTime to String
        return dto;
    }


}
