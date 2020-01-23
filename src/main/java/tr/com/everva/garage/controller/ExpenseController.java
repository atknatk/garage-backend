package tr.com.everva.garage.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.expense.ExpenseAddOrUpdateDto;
import tr.com.everva.garage.model.dto.expense.ExpenseDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.service.ExpenseService;
import tr.com.everva.garage.service.VehicleService;
import tr.com.everva.garage.validation.ValidUUID;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@ValidUUID @PathVariable("id") String id) {
        ExpenseDto expenseDto = expenseService.getById(id);
        ResponseDto response = ResponseDto.builder().success(true).data(expenseDto).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@ValidUUID @PathVariable("id") String id, @Valid @RequestBody ExpenseAddOrUpdateDto dto) {
        ExpenseDto updated = expenseService.update(id, dto);
        ResponseDto response = ResponseDto.builder().success(true).data(updated).build();
        return ResponseEntity.ok(response);
    }
}
