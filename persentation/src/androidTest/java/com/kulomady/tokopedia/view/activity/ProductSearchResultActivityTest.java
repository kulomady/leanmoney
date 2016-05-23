package com.kulomady.tokopedia.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.kulomady.tokopedia.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class ProductSearchResultActivityTest extends ActivityInstrumentationTestCase2<ProductSearchResultActivity> {

  private ProductSearchResultActivity productSearchResultActivity;

  public ProductSearchResultActivityTest() {
    super(ProductSearchResultActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    productSearchResultActivity = getActivity();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsProductListFragment() {
    Fragment productListFragment =
        productSearchResultActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(productListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.productSearchResultActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("MyTokopedia"));
  }

  private Intent createTargetIntent() {
    return ProductSearchResultActivity.getCallingIntent(getInstrumentation().getTargetContext(),"tas");
  }
}
