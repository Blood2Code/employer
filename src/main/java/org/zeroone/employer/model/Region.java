package org.zeroone.employer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region {
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
}
