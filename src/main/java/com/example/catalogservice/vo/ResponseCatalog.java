package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String musicID;
    private String musicName;
    private String musicArtistName;
    private String musicWriterName;
    private String musicLiricstName;
    private Integer musicStock;
    private Integer musicPrice;
    private String musicGenre;
    private Date createAt;



}
