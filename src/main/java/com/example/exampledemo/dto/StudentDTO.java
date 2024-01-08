package com.example.exampledemo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class StudentDTO {
    private String id;
    private String name;
    private String address;
}
