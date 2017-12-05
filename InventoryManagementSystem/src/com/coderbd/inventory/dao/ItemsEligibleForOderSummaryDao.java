/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.inventory.dao;

import com.coderbd.inventory.domain.ItemsEligibleForOder;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rajaul Islam
 */
public interface ItemsEligibleForOderSummaryDao {

    public void saveList(Set<ItemsEligibleForOder> items);

    public void updateList(Set<ItemsEligibleForOder> items);

    public List<ItemsEligibleForOder> getList();

    public ItemsEligibleForOder getItemsEligibleForOderByOrderStatusAndProductCode(String productCode, String orderStatus);


    public ItemsEligibleForOder getItemEligibleForOderByOrderStatus(String orderStatus);
    public List<ItemsEligibleForOder> getItemsEligibleForOderByOrderStatus(String orderStatus);
}
