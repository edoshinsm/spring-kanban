package myApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "columns")
@SequenceGenerator(name = "column_seq", sequenceName = "column_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntityColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long position;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private EntityProject project;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityIssue> issues = new ArrayList<>();
}
