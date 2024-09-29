package org.zeroone.employer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, length = 14)
    private String pinfl;
    @Column(nullable = false)
    private LocalDateTime hireDate;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
