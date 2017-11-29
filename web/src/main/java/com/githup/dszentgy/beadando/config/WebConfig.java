package com.githup.dszentgy.beadando.config;

import com.githup.dszentgy.beadando.dao.DryGoodsDAO;
import com.githup.dszentgy.beadando.dao.NewspaperDAO;
import com.githup.dszentgy.beadando.dao.PostOfficeDAO;
import com.githup.dszentgy.beadando.dao.mysql.DryGoodsDAOsql;
import com.githup.dszentgy.beadando.dao.mysql.NewspaperDAOsql;
import com.githup.dszentgy.beadando.dao.mysql.PostOfficeDAOsql;
import com.githup.dszentgy.beadando.drygoods.controller.DryGoodsController;
import com.githup.dszentgy.beadando.service.DryGoodsService;
import com.githup.dszentgy.beadando.service.DryGoodsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean(value = "drygoodsdao")
    public DryGoodsDAO initDryGoodsDAO(){
        return new DryGoodsDAOsql();
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