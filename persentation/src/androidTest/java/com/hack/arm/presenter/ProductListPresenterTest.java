/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.hack.arm.mapper.ProductModelDataMapper;
import com.hack.arm.view.ProductListView;
import com.hack.domain.interactor.GetList;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ProductListPresenterTest extends AndroidTestCase {

    private ProductListPresenter productListPresenter;

    @Mock
    private Context mockContext;
    @Mock
    private ProductListView mockProductListView;
    @Mock
    private GetList mockGetProductList;

    @Mock
    com.hack.domain.interactor.GetListByQuery mockGetListByQuery;

    @Mock
    private ProductModelDataMapper mockProductModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        productListPresenter = new ProductListPresenter(
                mockGetProductList,
                mockGetListByQuery,
                mockProductModelDataMapper
        );

        productListPresenter.setView(mockProductListView);
    }

    @Test
    public void testProductListPresenterInitialize() {
        given(mockProductListView.context()).willReturn(mockContext);
        productListPresenter.initialize();

        verify(mockProductListView).hideRetry();
        verify(mockProductListView).showLoading();
        verify(mockGetProductList).execute(any(Subscriber.class));
    }
}
