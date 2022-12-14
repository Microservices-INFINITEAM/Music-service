package com.example.catalogservice.jpa;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name ="catalog")
public class CatalogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =120, unique= false)
    private String musicId;
    @Column(nullable = false)
    private String musicName;
    @Column(nullable = false)
    private Integer musicStock;
    @Column(nullable = false)
    private Integer musicPrice;
    @Column(nullable = false)
    private String musicArtistname;
    @Column(nullable = false)
    private String musicWritername;
    @Column(nullable = false)
    private String musicLiricistname;
    @Column(nullable = false)
    private String musicGenre;


    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault( value = "CURRENT_TIMESTAMP")
    private Date createAt;


}
