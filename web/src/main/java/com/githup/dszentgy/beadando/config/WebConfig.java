package com.githup.dszentgy.beadando.config;

import com.githup.dszentgy.beadando.dao.DryGoodsDAO;
import com.githup.dszentgy.beadando.dao.JSON.DryGoodsDAOJSON;
import com.githup.dszentgy.beadando.drygoods.controller.DryGoodsController;
import com.githup.dszentgy.beadando.service.DryGoodsService;
import com.githup.dszentgy.beadando.service.DryGoodsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean(value = "drygoodsdao")
    public DryGoodsDAO initDryGoodsDAO(){
        return new DryGoodsDAOJSON("\\projekt\\WebTechWebAplBeadando\\adat.json");
    }
    @Bean(value = "drygoods")
    public DryGoodsService initDryGoodsService(){
        return new DryGoodsServiceImp(initDryGoodsDAO());
    }
    @Bean(value = "drygoodscontroller")
    public DryGoodsController initDryGoodsController(){
        return new DryGoodsController(initDryGoodsService());
    }


}