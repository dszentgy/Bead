package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.dao.DryGoodsDAO;
import com.githup.dszentgy.beadando.exception.NotFoundCategoryException;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.exception.NotFoundTypeException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.model.DryGoodsCategory;

import java.util.Collection;

public class DryGoodsServiceImp implements DryGoodsService {
    public DryGoodsDAO dryGoodsDAO;

    public DryGoodsServiceImp(DryGoodsDAO dryGoodsDAO) {
        this.dryGoodsDAO = dryGoodsDAO;
    }
    @Override
    public void addDryGoods(DryGoods dryGoods) throws NotFoundNameException{
        DryGoods old = dryGoodsDAO.readDryGoodsByName(dryGoods.getName());
        if (old == null) {
            dryGoodsDAO.createDryGoods(dryGoods);
        } else {

            DryGoods updated = new DryGoods(
                    dryGoods.getCategory(),
                    dryGoods.getType(),
                    dryGoods.getName(),
                    dryGoods.getPrice() + old.getPrice(),
                    dryGoods.getQuantity() + old.getQuantity());

            dryGoodsDAO.updateDryGoods(updated);
        }
    }

    @Override
    public Collection<DryGoods> getDryGoods() {
        return dryGoodsDAO.readDryGoods();
    }

    @Override
    public DryGoods getDryGoods(String name) throws NotFoundNameException {
        DryGoods old = dryGoodsDAO.readDryGoodsByName(name);
        if (old == null) {
            throw new NotFoundNameException("Does not exist it's a drygoods name");
        } else {
            return dryGoodsDAO.readDryGoodsByName(name);
        }
    }

    @Override
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundTypeException, NotFoundNameException, NotFoundCategoryException{
        DryGoods dryGoodsoldName = dryGoodsDAO.readDryGoodsByName(dryGoods.getName());
        if (dryGoodsoldName == null){
            throw new NotFoundNameException("Does not exist it's a drygoods name");
        }
        else{
                    dryGoodsDAO.deleteDryGoods(dryGoods);
                }



    }
}
