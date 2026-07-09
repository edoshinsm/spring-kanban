package myApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_members", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "project_id"}))
@SequenceGenerator(name = "project_members_seq", sequenceName = "project_members_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntityProjectMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_members_seq")
    private Long id;

    @Column(nullable = false)
    private String role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private EntityUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private EntityProject project;
}
