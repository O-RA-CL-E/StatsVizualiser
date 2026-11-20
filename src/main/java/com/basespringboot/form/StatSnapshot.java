package com.basespringboot.form;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (
    name = "stat_snapshot",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_snapshot_account_date",
        columnNames = {"account_id", "snapshot_date"}
    )
)

@Getter
@Setter
public class StatSnapshot {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "snapshot_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long followers;

    private Integer posts;

    private Long impressions;

    private Double engagementRate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
