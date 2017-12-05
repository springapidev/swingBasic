package com.coderbd.inventory.dao;

import com.coderbd.inventory.domain.StockSummary;
import com.coderbd.mobile.domain.User;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public interface StockSummaryDao {

    void save(StockSummary s);

    void update(StockSummary s);

    public void updateWhenSales(StockSummary s);

    void delete(int id);

    List<StockSummary> getList();

    User getProductSummary(int id);

    public StockSummary getProductSummaryByProductCode(String productCode);

    public StockSummary getProductSummaryById(int id);
    
     public List<StockSummary> getListForOrdersWhenStockLow(int minAvaileQty);
}
