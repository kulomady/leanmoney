/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.interactor;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Product}.
 */
public class GetDetails extends UseCase {

  private final int userId;
  private final com.hack.domain.repository.ProductRepository productRepository;

  @Inject
  public GetDetails(int userId, com.hack.domain.repository.ProductRepository productRepository,
                    com.hack.domain.executor.ThreadExecutor threadExecutor, com.hack.domain.executor.PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userId = userId;
    this.productRepository = productRepository;
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.productRepository.product(this.userId);
  }
}
