package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.example.catalogservice.vo.RequestNum;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogServiceImpl implements  CatalogService{
    CatalogRepository repository;

    Environment env;





    @Autowired
    public CatalogServiceImpl(CatalogRepository repository, Environment env){
        this.repository = repository;
        this.env = env;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs(){
        return repository.findAll();
    }


    @Override
    public String modifyCount(RequestNum musicStock) {


        ModelMapper mapper = new ModelMapper();
        CatalogDto catalogDto = mapper.map(musicStock, CatalogDto.class);

        CatalogEntity catalogEntity =repository.findBymusicId(catalogDto.getMusicId());

        catalogEntity.setMusicStock(catalogEntity.getMusicStock()+catalogDto.getMusicStock());
        repository.save(catalogEntity);

        String musicId = catalogEntity.getMusicId();


        int stocknum = catalogEntity.getMusicStock();

        return musicId+": "+"updated stock"+stocknum;
    }

    @Override
    public CatalogDto addSoundtrack(CatalogDto catalogDto) {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CatalogEntity catalogEntity=mapper.map(catalogDto, CatalogEntity.class);
        repository.save(catalogEntity);
        return null;
    }

    @Override
    public CatalogDto getMusicByMusicId(String musicId){
        CatalogEntity catalogEntity = repository.findBymusicId(musicId);


        CatalogDto catalogDto = new ModelMapper().map(catalogEntity, CatalogDto.class);


        return catalogDto;
    }

//    @Override
//    public  Integer modifyCount(CatalogDto catalogDto) {
//
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        CatalogEntity updateEntity = mapper.map(catalogDto, CatalogEntity.class);
//
//        updateEntity.getMusicId();
//
//        updateEntity.setMusicStock(CatalogRepository.findBymusicId2(updateEntity.getMusicId()));
//
//
//        return System.out.println("Stock is successfully update!");
//
//    }





}
