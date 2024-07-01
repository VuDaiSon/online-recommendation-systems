package com.example.recommendershop.dto.category.response;

import com.example.recommendershop.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAvatar extends BaseDto {
    private String name;
    private String image;
    private Date date;

}
