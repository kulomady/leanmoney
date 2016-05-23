package com.kulomady.data.cache.serializer;
import com.google.gson.Gson;
import com.kulomady.data.entity.response.ProductBean;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class product as Serializer/Deserializer for product entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  public String serialize(ProductBean productEntity) {
    return gson.toJson(productEntity, ProductBean.class);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link ProductBean}
   */
  public ProductBean deserialize(String jsonString) {
    return gson.fromJson(jsonString, ProductBean.class);
  }
}
