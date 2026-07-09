package myApp.model;

import jakarta.persistence.*;
import lombok.*;

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
    private int position;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private EntityProject project;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityIssue> issues = new ArrayList<>();
}
