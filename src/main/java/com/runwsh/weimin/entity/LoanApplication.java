
package com.runwsh.weimin.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanApplication {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private Integer term;
    private String status;
    private String reason;
    private LocalDateTime appliedAt;
    private LocalDateTime reviewedAt;
}
