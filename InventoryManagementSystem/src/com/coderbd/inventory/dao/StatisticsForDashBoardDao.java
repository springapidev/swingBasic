/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.inventory.dao;

import com.coderbd.inventory.domain.StatisticsForDashBoard;
import com.coderbd.inventory.domain.StockSummary;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rajaul Islam
 */
public interface StatisticsForDashBoardDao {

    List<StatisticsForDashBoard> getItemStatisticsTillToday();

    public List<StatisticsForDashBoard> getItemStatisticsByBrandOrCategoryNameTillToday(String category);
    public Set<StatisticsForDashBoard> getListOfProductCategory();
    public StatisticsForDashBoard getProductCategory();
}
