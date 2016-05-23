package com.kulomady.tokopedia.view;


import com.kulomady.tokopedia.model.ProductModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link ProductModel}.
 */
public interface ProductListView extends LoadDataView {

  void renderProductList(Collection<ProductModel> productModelCollection);
  void renderMoreProductList(Collection<ProductModel> productModelCollection);

  void viewProduct(ProductModel productModel);

  void showLoadingLoadmoreProduct();

  void hideLoadingLoadmoreProduct();

  void setMoreLoading(boolean isMoreLoading);
}
