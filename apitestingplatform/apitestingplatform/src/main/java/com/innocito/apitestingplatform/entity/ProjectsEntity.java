package com.innocito.apitestingplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "projects_details")
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tc.No.", nullable = false, unique = true)
    private Long id;
    @Column(name = "Project Name", nullable = false, unique = true)
    private String project_name;
    @Column(name = "Description", nullable = false)
    private String description;
    @Column(name = "Type", nullable = false)
    private String type;
    @Column(name = "Created By", nullable = false)
    private String created_by;

    @CreationTimestamp
    @Column(name = "Creation Date")
    private LocalDate creation_date;

}
