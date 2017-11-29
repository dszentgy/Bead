package com.githup.dszentgy.beadando.drygoods.controller;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.service.DryGoodsService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
@RequestMapping("/drygoods")
public class DryGoodsController {

    private DryGoodsService dryGoodsService;
    public DryGoodsController(DryGoodsService dryGoodsService) {
        this.dryGoodsService = dryGoodsService;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<DryGoods> listAllDryGoods(){
        return dryGoodsService.getDryGoods();
    }

    @RequestMapping(value = {"/{Name}"}, method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DryGoods listAllDryGoodsName( @PathVariable(value = "Name") String name)throws NotFoundNameException{
        return dryGoodsService.getDryGoods(name);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DryGoods addDryGoods(@RequestBody DryGoods dryGoods) throws Exception{
        dryGoodsService.addDryGoods(dryGoods);
        return dryGoods;
    }



}
