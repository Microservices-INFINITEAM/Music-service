package com.example.catalogservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class RequestNum {
    private String musicId;
    private Integer musicStock;

}
