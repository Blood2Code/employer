package org.zeroone.employer.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<Т> {
    private HttpStatus status;
    private Boolean success;
    private String message;
    private Т data;
}
