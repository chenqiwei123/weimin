
package com.runwsh.weimin.controller;

import com.runwsh.weimin.entity.LoanApplication;
import com.runwsh.weimin.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getLoanById(@PathVariable Long id) {
        LoanApplication loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanApplication>> getLoansByUserId(@PathVariable Long userId) {
        List<LoanApplication> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(loans);
    }
}
