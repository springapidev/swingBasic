package com.coderbd.inventory.service;

import com.coderbd.inventory.dao.StatisticsForDashBoardDao;
import com.coderbd.inventory.domain.StatisticsForDashBoard;
import com.coderbd.inventory.domain.StockSummary;
import com.coderbd.mobile.conn.MySqlDbConnection;
import com.coderbd.mobile.domain.Product;
import com.coderbd.mobile.service.ProductService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rajaul Islam
 */
public class StatisticsForDashBoardService implements StatisticsForDashBoardDao {

    Connection conn = MySqlDbConnection.getConnection();
    ProductService productService = new ProductService();
    StockSummaryService stockSummaryService = new StockSummaryService();

    @Override
    public List<StatisticsForDashBoard> getItemStatisticsTillToday() {
        List<StatisticsForDashBoard> list = new ArrayList<>();
        StatisticsForDashBoard sd = null;
        Product product = null;
        for (StockSummary stockSummary : stockSummaryService.getList()) {
            product = productService.getProductByProductCode(stockSummary.getProductCode());
            sd = new StatisticsForDashBoard(stockSummary.getProductName(), stockSummary.getProductCode(), product.getCategory(), stockSummary.getPurchaseQty(), stockSummary.getSoldQty(), stockSummary.getAvilableQty());
            list.add(sd);
        }
        return list;
    }

    @Override
    public List<StatisticsForDashBoard> getItemStatisticsByBrandOrCategoryNameTillToday(String category) {
        List<StatisticsForDashBoard> list = new ArrayList<>();
        int itemIn = 0, itemOut = 0, itemAvailable = 0;
        StatisticsForDashBoard sd = null;
        Product product = null;

        StatisticsForDashBoard statistics = getProductCategory();

        for (Product p : productService.getListOrderByCategory()) {
            StockSummary stockSummary = stockSummaryService.getProductSummaryByProductCode(p.getProductCode());

            if (statistics.getBrandOrCategory().equals("Nokia")) {
                itemIn += stockSummary.getPurchaseQty();
                itemOut += stockSummary.getSoldQty();
                itemAvailable += stockSummary.getAvilableQty();

            } else if(p.getCategory().equals(statistics.getBrandOrCategory()))  {
                itemIn += stockSummary.getPurchaseQty();
                itemOut += stockSummary.getSoldQty();
                itemAvailable += stockSummary.getAvilableQty();
            } else if (p.getCategory().equals(statistics.getBrandOrCategory()))  {
                itemIn += stockSummary.getPurchaseQty();
                itemOut += stockSummary.getSoldQty();
                itemAvailable += stockSummary.getAvilableQty();

            } else if (p.getCategory().equals("Symphony")) {
                itemIn += stockSummary.getPurchaseQty();
                itemOut += stockSummary.getSoldQty();
                itemAvailable += stockSummary.getAvilableQty();

            }

            sd = new StatisticsForDashBoard(p.getName(), p.getProductCode(), p.getCategory(), itemIn, itemOut, itemAvailable);
            list.add(sd);
        }
        return list;
    }

    @Override
    public Set<StatisticsForDashBoard> getListOfProductCategory() {
        Set<StatisticsForDashBoard> sets = new HashSet<>();
        StatisticsForDashBoard sd = null;
        for (Product p : productService.getListOrderByCategory()) {
            sd = new StatisticsForDashBoard(p.getCategory());
            sets.add(sd);
        }
        return sets;
    }

    @Override
    public StatisticsForDashBoard getProductCategory() {
        Product p = productService.getProductByCategory();
        StatisticsForDashBoard sfdb = new StatisticsForDashBoard(p.getCategory());
        return sfdb;
    }
}
