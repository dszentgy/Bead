package com.githup.dszentgy.beadando.dao;

import com.githup.dszentgy.beadando.exception.DryGoodsNameIsOccupiedException;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.model.DryGoodsCategory;


import javax.naming.NameNotFoundException;
import java.util.Collection;

public interface DryGoodsDAO {
    public Collection<DryGoods> readDryGoods();
    public DryGoods readDryGoodsByName(String name) throws NotFoundNameException;
    public void createDryGoods(DryGoods dryGoods) throws DryGoodsNameIsOccupiedException;
    public void updateDryGoods(DryGoods dryGoods) throws NotFoundNameException;
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundNameException;

}
