package com.praneethpj.jobms.job.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
   private String title;
   private String description;
   private String minSalary;
   private String maxSalary;
   private String location;
   private Long companyId;


}
