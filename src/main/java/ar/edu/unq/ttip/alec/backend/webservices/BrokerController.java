package ar.edu.unq.ttip.alec.backend.webservices;

import ar.edu.unq.ttip.alec.backend.model.Broker;
import ar.edu.unq.ttip.alec.backend.service.BrokerService;
import ar.edu.unq.ttip.alec.backend.service.dtos.BrokerDTO;
import ar.edu.unq.ttip.alec.backend.service.dtos.CalcResultDTO;
import ar.edu.unq.ttip.alec.backend.service.dtos.CalculationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/broker")
@Api(value="/broker",tags="Tax Broker Controller",description = "Manage ALEC Tax Brokers and calculations")
public class BrokerController {


    @Autowired
    private BrokerService service;

    @GetMapping
    @ApiOperation("List all Tax Brokers")
    public ResponseEntity<List<Broker>> getAllBrokers() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/create")
    @ApiOperation("Allow to create a Tax Broker")
    public ResponseEntity<BrokerDTO> createBroker(@RequestBody BrokerDTO request) {
        return new ResponseEntity(
                service.createBroker(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/calculate")
    @ApiOperation("Allow to calculate a Tax aplication.")
    public ResponseEntity<CalcResultDTO> calculate(@RequestBody CalculationDTO request) {
        return new ResponseEntity(
                service.calculate(request.getAmount(),request.getApartado(),request.getTaxId()),
                HttpStatus.CREATED
        );
    }




}