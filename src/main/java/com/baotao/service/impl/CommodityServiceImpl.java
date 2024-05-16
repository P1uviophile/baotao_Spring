package com.baotao.service.impl;

import com.baotao.bean.Commodity;
import com.baotao.dao.ICommodityDao;
import com.baotao.dao.impl.CommodityDaoImpl;
import com.baotao.service.ICommodityService;

import java.util.List;

public class CommodityServiceImpl implements ICommodityService {

    private ICommodityDao CommodityDao = new CommodityDaoImpl();
    @Override
    public List<Commodity> getCommodity( ) {
        return CommodityDao.list();
    }

    @Override
    public List<Commodity> getCommodityCondition(String str) {
        return CommodityDao.listCondition(str);
    }

    @Override
    public Integer addCommodity(Commodity commodity) {
        return CommodityDao.save(commodity);
    }

    @Override
    public Integer deleteByCommodity_id(Integer Commodity_id) {
        return CommodityDao.deleteByCommodity_id(Commodity_id);
    }

    @Override
    public Commodity queryByCommodity_id(Integer Commodity_id) {
        return CommodityDao.queryByCommodity_id(Commodity_id);
    }

    @Override
    public Integer updateCommodity(Commodity commodity) {
        return CommodityDao.updateCommodity(commodity);
    }

    @Override
    public Integer buyingCommodity(Commodity commodity, Integer Commodity_id, Integer user_id, Integer count) {
        return CommodityDao.buyingCommodity(commodity, Commodity_id, user_id, count);
    }
}
