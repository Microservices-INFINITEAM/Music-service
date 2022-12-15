package com.example.catalogservice.controller;


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
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService){
        this.env = env;
        this.catalogService = catalogService;
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



    @GetMapping(value ="/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(v ->{
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "/catalogs/change")
    public String  changeCatalogNum( @RequestBody RequestNum musicStock){

        String get  =   catalogService.modifyCount(musicStock);

        return "Stock is successfully updated"+"\n"+get;
    }


    @PostMapping(value = "/catalogs/add")
    public String  addCatalog(@RequestBody RequestCatalog music){
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CatalogDto catalogDto=mapper.map(music,CatalogDto.class);
        catalogService.addSoundtrack(catalogDto);
        return "Catalog is created";
    }

//    get music-service/musics/{musicId}
    @GetMapping(value = "/catalog/{musicId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseCatalog> getMusic(@PathVariable("musicId") String musicId){
        CatalogDto catalogDto = catalogService.getMusicByMusicId(musicId);
        ResponseCatalog returnvalue = new ModelMapper().map(catalogDto, ResponseCatalog.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);

    }



}
