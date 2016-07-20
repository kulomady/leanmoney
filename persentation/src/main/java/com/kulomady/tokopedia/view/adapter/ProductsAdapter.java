package com.kulomady.tokopedia.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.model.ProductModel;
import com.kulomady.tokopedia.view.viewComponent.ProductsLayoutManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link ProductModel}.
 */
public class ProductsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickListener {
        void onProductItemClicked(ProductModel productModel);
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    private Context context;

    public final int VIEW_ITEM = 1;


    private OnLoadMoreListener onLoadMoreListener;
    private ProductsLayoutManager mLayoutManager;


    private List<ProductModel> productsCollection;

    private OnItemClickListener onItemClickListener;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public ProductsAdapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener=onLoadMoreListener;
        this.productsCollection = Collections.emptyList();
    }


    @Override
    public int getItemCount() {
        return (this.productsCollection != null) ? this.productsCollection.size() : 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == VIEW_ITEM) {
            return new ProductViewHolder(
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_product, parent, false)
            );

        } else {
            return new ProgressViewHolder(
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_progress, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            final ProductModel productModel = this.productsCollection.get(position);
            ((ProductViewHolder)holder).textViewTitle.setText(productModel.getShop_name());

            ((ProductViewHolder) holder).textViewProductName.setText(productModel.getProduct_name());
            ((ProductViewHolder) holder).textViewProductPrice.setText(productModel.getProduct_price());

            ((ProductViewHolder) holder).textViewGrosir.setVisibility(
                    productModel.getProduct_wholesale()>0 ? View.VISIBLE :View.GONE
            );

            displayProductImage((ProductViewHolder) holder, productModel);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ProductsAdapter.this.onItemClickListener != null) {
                        ProductsAdapter.this.onItemClickListener.onProductItemClicked(productModel);
                    }
                }
            });
        }
    }

    private void displayProductImage(final ProductViewHolder holder, ProductModel productModel) {
        Picasso.with(context)
                .load(productModel.getProduct_image())
                .into(holder.productImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.imageProductProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.imageProductProgress.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setProductsCollection(Collection<ProductModel> usersCollection) {
        this.validateProductsCollection(usersCollection);
        this.productsCollection = (List<ProductModel>) usersCollection;
        this.notifyDataSetChanged();
    }

    public List<ProductModel> getProductsCollection() {
        return productsCollection;
    }

    public void setLayoutManager(ProductsLayoutManager linearLayoutManager){
        this.mLayoutManager =linearLayoutManager;

    }

    public void addItemsMore(List<ProductModel> lst){
        this.productsCollection.addAll(lst);
        notifyItemRangeChanged(0,productsCollection.size());
    }

    public void setRecyclerView(RecyclerView mView){
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (!isMoreLoading && (totalItemCount - visibleItemCount)<= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        int VIEW_PROG = 0;
        return this.productsCollection.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading=isMoreLoading;
    }

    public void setProgressMore(final boolean isProgress) {
        if (isProgress) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    productsCollection.add(null);
                    notifyItemInserted(productsCollection.size() - 1);
                }
            });
        } else {
            productsCollection.remove(productsCollection.size() - 1);
            notifyItemRemoved(productsCollection.size());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateProductsCollection(Collection<ProductModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_shop_name)
        TextView textViewTitle;
        @BindView(R.id.product_name)
        TextView textViewProductName;
        @BindView(R.id.product_price)
        TextView textViewProductPrice;

        @BindView(R.id.tv_grosir)
        TextView textViewGrosir;

        @BindView(R.id.iv_product_image)
        ImageView productImage;
        @BindView(R.id.pbImageProduct)
        ProgressBar imageProductProgress;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pBar;
        public ProgressViewHolder(View v) {
            super(v);
            pBar = (ProgressBar) v.findViewById(R.id.pBar);
        }
    }
}
