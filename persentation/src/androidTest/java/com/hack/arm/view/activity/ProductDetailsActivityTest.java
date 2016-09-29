/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.view.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.hack.arm.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class ProductDetailsActivityTest extends ActivityInstrumentationTestCase2<ProductDetailActivity> {

  private static final int FAKE_PRODUCT_ID = 10;

  private ProductDetailActivity productDetailsActivity;

  public ProductDetailsActivityTest() {
    super(ProductDetailActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    this.productDetailsActivity = getActivity();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsProductDetailsFragment() {
    Fragment productDetailsFragment =
        productDetailsActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(productDetailsFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.productDetailsActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("Product Details"));
  }

  public void testLoadProductHappyCaseViews() {
    onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
    onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

    onView(withId(R.id.tv_fullname)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_email)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
  }

  public void testLoadProductHappyCaseData() {
    onView(withId(R.id.tv_fullname)).check(matches(withText("Mady")));
    onView(withId(R.id.tv_email)).check(matches(withText("kulomady@gmail.com")));
    onView(withId(R.id.tv_followers)).check(matches(withText("4523")));
  }

  private Intent createTargetIntent() {
    Context context = getInstrumentation().getTargetContext();
    return ProductDetailActivity.getCallingIntent(context, FAKE_PRODUCT_ID);
  }
}
