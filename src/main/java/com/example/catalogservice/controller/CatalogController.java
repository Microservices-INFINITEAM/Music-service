package com.example.catalogservice.controller;


import com.example.catalogservice.Katalog.KafkaProducer;
import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/music-service")
public class CatalogController {
    private Environment env;
    CatalogService catalogService;
    KafkaProducer kafkaProducer;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService){
        this.env = env;
        this.catalogService = catalogService;
        this.kafkaProducer =  kafkaProducer;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in Catalog Service on Port %s", request.getServerPort());
    }

//    @PostMapping(value="/")
//    public
//
//    @GetMapping(value="/ablecatalogs")
//    public ResponseEntity<List<ResponseCatalog>> getableCatalogs(){
//        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();
//
//        List<ResponseCatalog> result = new ArrayList<>();
//        orderList.forEach(v ->{
//            result.add(new ModelMapper().map(v, ResponseCatalog.class));
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }



    @GetMapping(value ="/musics")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(v ->{
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "/musics/change")
    public String  changeCatalogNum( @RequestBody RequestNum musicStock){

        String get  =   catalogService.modifyCount(musicStock);

        return "Stock is successfully updated"+"\n"+get;
    }


    @PostMapping(value = "/musics/add")
    public String  addCatalog(@RequestBody RequestCatalog music){
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CatalogDto catalogDto=mapper.map(music,CatalogDto.class);
        catalogService.addSoundtrack(catalogDto);
        return "Soundtrack is successfully created";
    }

//    get music-service/musics/{musicId}
    @GetMapping(value = "/musics/{musicId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseCatalog> getMusic(@PathVariable("musicId") String musicId){
        CatalogDto catalogDto = catalogService.getMusicByMusicId(musicId);
        ResponseCatalog returnvalue = new ModelMapper().map(catalogDto, ResponseCatalog.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);

    }

    @GetMapping(value = "/{musicId}/{userId}")
    public ResponseEntity<ResponseCatalog> CheckMusic (@PathVariable("musicId") String musicId, @PathVariable("userId") String userId){

        CatalogDto catalogDto = catalogService.getMusicByMusicId(musicId);
        ResponseCatalog returnvalue = new ModelMapper().map(catalogDto, ResponseCatalog.class);

        kafkaProducer.send("music-count-topic",catalogDto);

        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);
    }

}
