package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.exception.NotFoundCategoryException;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.exception.NotFoundTypeException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.model.DryGoodsCategory;

import java.util.Collection;

public interface DryGoodsService {

    public void addDryGoods(DryGoods dryGoods) throws NotFoundNameException;
    public Collection<DryGoods> getDryGoods();
    public DryGoods getDryGoods(String name)throws NotFoundNameException;
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundNameException, NotFoundTypeException, NotFoundCategoryException;

}
