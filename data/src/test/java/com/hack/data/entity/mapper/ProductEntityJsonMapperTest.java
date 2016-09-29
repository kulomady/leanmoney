/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.mapper;

import com.google.gson.JsonSyntaxException;
import com.hack.data.entity.response.ProductBean;
import com.hack.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductEntityJsonMapperTest extends ApplicationTestCase {

  private static final String JSON_RESPONSE_PRODUCT_DETAILS = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private static final String JSON_RESPONSE_PRODUCT_COLLECTION = "[{\n"
      + "    \"id\": 1,\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"followers\": 7484\n"
      + "}, {\n"
      + "    \"id\": 12,\n"
      + "    \"full_name\": \"Pedro Garcia\",\n"
      + "    \"followers\": 1381\n"
      + "}]";

  private ProductEntityJsonMapper productEntityJsonMapper;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    productEntityJsonMapper = new ProductEntityJsonMapper();
  }

  @Test
  public void testTransformUserEntityHappyCase() {
    ProductBean userEntity = productEntityJsonMapper.transformUserEntity(JSON_RESPONSE_PRODUCT_DETAILS);

//    assertThat(userEntity.getUserId(), is(1));
//    assertThat(userEntity.getFullname(), is(equalTo("Simon Hill")));
//    assertThat(userEntity.getEmail(), is(equalTo("jcooper@babbleset.edu")));
  }

  @Test
  public void testTransformUserEntityCollectionHappyCase() {
//    Collection<ProductEntity> userEntityCollection =
//        productEntityJsonMapper.transformUserEntityCollection(
//                JSON_RESPONSE_PRODUCT_COLLECTION);
//
//    assertThat(((ProductEntity) userEntityCollection.toArray()[0]).getUserId(), is(1));
//    assertThat(((ProductEntity) userEntityCollection.toArray()[1]).getUserId(), is(12));
//    assertThat(userEntityCollection.size(), is(2));
  }

  @Test
  public void testTransformUserEntityNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    productEntityJsonMapper.transformUserEntity("ironman");
  }

  @Test
  public void testTransformUserEntityCollectionNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    productEntityJsonMapper.transformUserEntityCollection("Tony Stark");
  }
}
