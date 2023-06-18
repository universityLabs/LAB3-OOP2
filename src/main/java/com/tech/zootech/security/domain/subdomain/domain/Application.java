package com.tech.zootech.security.domain.subdomain.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
@Table(name = "applications",
        indexes = {
        @Index(name = "applications_applicant_id_idx", columnList = "applicant_id"),
        @Index(name = "applications_advisor_id_idx", columnList = "advisor_id")
})
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private BigDecimal amountUsd;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime assignedAt;

    public void assignTo(Advisor advisor) {
        verifyNotAssigned();
        this.advisor = advisor;
        this.status = Status.ASSIGNED;
        this.assignedAt = LocalDateTime.now();
    }

    private void verifyNotAssigned() {
        if (nonNull(advisor)) {
            throw new IllegalArgumentException("Application is already assigned");
        }
    }

    public enum Status {
        NEW, ASSIGNED, APPROVED, CANCELLED, DECLINED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(amountUsd, that.amountUsd)) return false;
        if (status != that.status) return false;
        if (!Objects.equals(applicant, that.applicant)) return false;
        if (!Objects.equals(advisor, that.advisor)) return false;
        if (!Objects.equals(createdAt, that.createdAt)) return false;
        return Objects.equals(assignedAt, that.assignedAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amountUsd != null ? amountUsd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (advisor != null ? advisor.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (assignedAt != null ? assignedAt.hashCode() : 0);
        return result;
    }
}
