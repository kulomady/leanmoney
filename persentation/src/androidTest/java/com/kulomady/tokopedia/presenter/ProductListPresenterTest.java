package com.kulomady.tokopedia.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.kulomady.domain.interactor.GetListByQuery;
import com.kulomady.domain.interactor.UseCase;
import com.kulomady.tokopedia.mapper.ProductModelDataMapper;
import com.kulomady.tokopedia.view.ProductListView;

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
    private UseCase mockGetProductList;

    @Mock
    GetListByQuery mockGetListByQuery;

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

    public void testProductListPresenterInitialize() {
        given(mockProductListView.context()).willReturn(mockContext);
        productListPresenter.initialize();

        verify(mockProductListView).hideRetry();
        verify(mockProductListView).showLoading();
        verify(mockGetProductList).execute(any(Subscriber.class));
    }
}
