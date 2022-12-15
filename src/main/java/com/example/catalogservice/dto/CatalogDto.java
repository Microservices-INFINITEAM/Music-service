package com.example.catalogservice.dto;

import com.example.catalogservice.vo.ResponseCatalog;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CatalogDto implements Serializable {
    private String musicId;
    private Integer qty;
    private String userId;
    private Integer musicPrice;
    private Integer totalPrice;

    private String orderId;


    private String musicName;
    private String musicArtistName;
    private String musicWriterName;
    private String musicLiricistName;
    private Integer musicStock;

    private String musicGenre;
    private Date createAt;


//    private List<ResponseCatalog> orders;

    private List<ResponseCatalog> catalogs;




}
