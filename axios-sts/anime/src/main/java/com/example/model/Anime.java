//package com.example.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Anime {
//	
//	//Whichever attribute you mark with @ID will be primary key.
//		@Id  //primary key
//		@GeneratedValue(strategy  = GenerationType.IDENTITY)
//		private long id; //Now this is primary key.
//		
//		@NotNull
//		@Size(min = 0, max  = 50)
//		private String name;
//		
//		
//		@NotNull
//		@Size(min = 0, max  = 500000000)
//		private String synopsis;
//		
//		@NotNull
//		@Min(value = 0, message = "Value must be greater than or equal to 0")
//	    @Max(value = 10, message = "Value must be less than or equal to 10")
//		private float rating;
//		
//
//}



package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Anime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Size(min = 0, max = 50)
    private String name;
    
    @NotNull
    @Size(min = 0, max = 500000000)
    private String synopsis;
    
    @NotNull
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    @Max(value = 10, message = "Value must be less than or equal to 10")
    private float rating;

    public void setRating(float rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10");
        }
        this.rating = rating;
    }
}
