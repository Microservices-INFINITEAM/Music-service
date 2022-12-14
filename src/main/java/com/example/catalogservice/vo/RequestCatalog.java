package com.example.catalogservice.vo;

//import com.sun.istack.NotNull;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class RequestCatalog {

    @NotNull(message = "musicID cannot be null")
    @Size(min = 2, message ="musicID not be less than two characters")
    private String musicId;

    @NotNull(message= "musicName cannot be null")
    @Size(min = 8, message = "musicName must be equal or greater than 8 characters and less than 16 characters")
    private String musicName;

    @NotNull(message ="musicArtistName cannot be null")
    @Size(min = 2, message = "musicArtistName not be less than two characters")
    private String musicArtistname;

    @NotNull(message ="musicWriterName cannot be null")
    @Size(min = 2, message = "musicWriterName not be less than two characters")
    private String musicWritername;

    @NotNull(message ="musicliricistName cannot be null")
    @Size(min = 2, message = "musicliricistName not be less than two characters")
    private String musicLiricistname;

    @NotNull(message ="musicStock cannot be null")
    @Size(min = 2, message = "musicStock not be less than two characters")
    private Integer musicStock;

    @NotNull(message ="musicPrice cannot be null")
    @Size(min = 2, message = "musicPrice not be less than two characters")
    private Integer musicPrice;

    @NotNull(message ="musicGenre cannot be null")
    @Size(min = 2, message = "musicGenre not be less than two characters")
    private String musicGenre;






}
