package org.zeroone.employer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Organization parent;
}
