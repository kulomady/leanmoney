package com.kulomady.domain.interactor;

import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Product}.
 */
public class GetDetails extends UseCase {

  private final int userId;
  private final ProductRepository productRepository;

  @Inject
  public GetDetails(int userId, ProductRepository productRepository,
                    ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userId = userId;
    this.productRepository = productRepository;
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.productRepository.product(this.userId);
  }
}
