package de.neuefische.studentdbweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @NotBlank(message = "student id is required")
    private String id;

    @NotBlank(message = "student name is required")
    @Size(min = 2, max = 30, message = "student name is required")
    private String name;

}
