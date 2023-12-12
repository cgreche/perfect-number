package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.PerfectNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="perfect-number", produces="application/json")
public class PerfectNumberController {

    @Operation(summary = "Test a number as Perfect Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tested successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid number supplied")
    })
    @GetMapping(value="/test")
    public Boolean testPerfectNumber(
            @Parameter(description = "Number to be tested as Perfect Number") @RequestParam BigInteger number) {
        return PerfectNumber.test(number);
    }

    @Operation(summary = "Find all the Perfect Numbers between number1 and number2, inclusive")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value="/find-between")
    public List<BigInteger> findPerfectNumbers(
            @Parameter(description = "Lower bound number to start searching for Perfect Numbers") @RequestParam BigInteger number1,
            @Parameter(description = "Upper bound number to stop searching for Perfect Numbers") @RequestParam BigInteger number2) {
        return PerfectNumber.findBetween(number1, number2);
    }
}
