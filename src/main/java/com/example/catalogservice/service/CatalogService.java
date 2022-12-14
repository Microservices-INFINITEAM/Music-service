package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.vo.RequestNum;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();

//    CatalogDto modifyCount (CatalogDto catalogDto);

    String modifyCount(RequestNum musicStock);

    CatalogDto addSoundtrack(CatalogDto dto);

//    void modifyCount(CatalogDto catalogDto);
//    CatalogDto createCatalog(CatalogDto catalogDto);
}
