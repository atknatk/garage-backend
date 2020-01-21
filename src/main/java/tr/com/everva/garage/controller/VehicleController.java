package tr.com.everva.garage.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.expense.ExpenseAddDto;
import tr.com.everva.garage.model.dto.expense.ExpenseDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.Expense;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.service.ExpenseService;
import tr.com.everva.garage.service.VehicleService;
import tr.com.everva.garage.validation.ValidUUID;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ExpenseService expenseService;

    public VehicleController(VehicleService vehicleService, ExpenseService expenseService) {
        this.vehicleService = vehicleService;
        this.expenseService = expenseService;
    }


    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        List<Vehicle> vehicle = vehicleService.list();
        ResponseDto response = ResponseDto.builder().success(true).data(vehicle).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addVehicle(@Valid @RequestBody VehicleCreateDto dto) {
        Vehicle vehicle = vehicleService.create(dto);
        ResponseDto response = ResponseDto.builder().success(true).data(vehicle.getId()).build();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateVehicle(@ValidUUID @PathVariable("id") String id, @Valid @RequestBody VehicleUpdateDto dto) {
        Vehicle updated = vehicleService.update(id, dto);
        ResponseDto response = ResponseDto.builder().success(true).data(new VehicleUpdateDto(updated)).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/sold")
    public ResponseEntity<ResponseDto> sold(@ValidUUID @PathVariable("id") String id, @Valid @RequestBody VehicleSalesDto dto) {
        ResponseDto response = vehicleService.sold(id, dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/expense")
    public ResponseEntity<ResponseDto> addExpense(@ValidUUID @PathVariable("id") String id, @Valid @RequestBody ExpenseAddDto dto) {
        ResponseDto responseDto = expenseService.addExpense(id, dto);
        return ResponseEntity.ok(responseDto);
    }

}
