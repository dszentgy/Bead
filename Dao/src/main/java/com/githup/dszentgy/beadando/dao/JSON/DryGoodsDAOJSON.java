package com.githup.dszentgy.beadando.dao.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.githup.dszentgy.beadando.dao.DryGoodsDAO;
import com.githup.dszentgy.beadando.exception.DryGoodsNameIsOccupiedException;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.DryGoods;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class DryGoodsDAOJSON implements DryGoodsDAO{

   ObjectMapper objectMapper;
   File jsonfile;

    public DryGoodsDAOJSON(String filename) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        jsonfile = new File(filename);
        if(!jsonfile.exists()){
            try {
                jsonfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Collection<DryGoods> readDryGoods() {
        Collection<DryGoods> dryGoods = new HashSet<DryGoods>();
        try {
          System.out.println(jsonfile.getAbsoluteFile());
          dryGoods = objectMapper.readValue(jsonfile, new TypeReference<HashSet<DryGoods>>(){});


        }catch (IOException e){
           e.printStackTrace();
        }
        return dryGoods;
    }

    @Override
    public DryGoods readDryGoodsByName(String name) throws NotFoundNameException {
        Collection<DryGoods> dryGoods = new HashSet<DryGoods>();
        try {
            dryGoods = objectMapper.readValue(jsonfile, new TypeReference<HashSet<DryGoods>>(){});
            for (DryGoods e : dryGoods){
                if (e.getName().equalsIgnoreCase(name)){
                    return e;
                }
            }
        }catch (MismatchedInputException e){
            System.err.println("Empty file");
        }catch (IOException e){
            e.printStackTrace();
        }
        throw new  NotFoundNameException(name);
    }

    @Override
    public void createDryGoods(DryGoods dryGoods) throws DryGoodsNameIsOccupiedException {
        Collection<DryGoods> drygoodss = readDryGoods();

        boolean uniqueName = true;
        for (DryGoods e : drygoodss){
            if (e.getName().equals(dryGoods.getName()));{
                uniqueName = false;
            }
        }
        if (!uniqueName){
            throw new DryGoodsNameIsOccupiedException(dryGoods.getName());
        }
        drygoodss.add(dryGoods);
        try {
            objectMapper.writeValue(jsonfile, dryGoods);
        }catch (IOException e){
            e.printStackTrace();
        }
        }

    @Override
    public void updateDryGoods(DryGoods dryGoods) throws NotFoundNameException{
        Collection<DryGoods> drygoodss = readDryGoods();
        for (DryGoods e : drygoodss){
            if(e.getName().equalsIgnoreCase(dryGoods.getName())) {
                drygoodss.remove(e);
                drygoodss.add(dryGoods);
            }
        }
        try {
            objectMapper.writeValue(jsonfile, dryGoods);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundNameException{
    DryGoods removeDryGoods = readDryGoodsByName(dryGoods.getName());
    Collection<DryGoods> dryGoods1 = readDryGoods();
    dryGoods1.remove(removeDryGoods);
    try {
        objectMapper.writeValue(jsonfile, dryGoods);
    }catch (IOException e){
        e.printStackTrace();
    }
    }
}
