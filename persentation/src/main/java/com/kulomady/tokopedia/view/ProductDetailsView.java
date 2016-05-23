
package com.kulomady.tokopedia.view;


import com.kulomady.tokopedia.model.ProductModel;

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
