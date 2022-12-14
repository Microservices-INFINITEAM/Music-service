package com.example.catalogservice.jpa;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.vo.RequestNum;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {



    //Prodcut ID로 물건 검색
    CatalogEntity findBymusicId(String musicID);


//    CatalogEntity findBymusicId(String musicId);
}
