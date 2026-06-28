package com.dairybilling.api.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dairy_group")
@Data

public class DairyGroup {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    
    private String contactEmail;

	
}
