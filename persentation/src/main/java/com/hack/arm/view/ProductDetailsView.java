
/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.view;


import com.hack.arm.model.ProductModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a product profile.
 */
public interface ProductDetailsView extends LoadDataView {
  /**
   * Render a product in the UI.
   *
   * @param user The {@link ProductModel} that will be shown.
   */
  void renderUser(ProductModel user);
}
