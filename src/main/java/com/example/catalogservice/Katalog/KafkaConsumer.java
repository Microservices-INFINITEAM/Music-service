package com.example.catalogservice.Katalog;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.example.catalogservice.service.CatalogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    CatalogRepository repository;

    @Autowired
    public KafkaConsumer(CatalogRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "example-order-topic")
    public void processMessage(String kafkaMessage){
        log.info("Kafka Message: ====>" +kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

            CatalogEntity entity = repository.findBymusicId((String)map.get("musicId"));
            // Object 형태로 가져오는 데이터를 Integer 로 변환후 뺀 후 저장
            entity.setMusicStock(entity.getMusicStock()-(Integer)map.get("musicQty"));
            repository.save(entity);
        }

    @KafkaListener(topics = "back-order-topic")
    public void processMessage2(String kafkaMessage){
        log.info("Kafka Message: ====>" +kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        CatalogEntity entity = repository.findBymusicId((String)map.get("musicId"));
        // Object 형태로 가져오는 데이터를 Integer 로 변환후 뺀 후 저장
        entity.setMusicStock(entity.getMusicStock()+(Integer)map.get("musicQty"));
        repository.save(entity);
    }

}
