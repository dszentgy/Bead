package com.githup.dszentgy.beadando.dao;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.model.DryGoodsCategory;


import java.util.Collection;

public interface DryGoodsDAO {
    public Collection<DryGoods> readDryGoods();
    public DryGoods readDryGoodsByName(String name);
    public void createDryGoods(DryGoods dryGoods);
    public void updateDryGoods(DryGoods dryGoods) throws NotFoundNameException;
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundNameException;

}
