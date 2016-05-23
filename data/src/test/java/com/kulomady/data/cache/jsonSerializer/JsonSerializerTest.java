
package com.kulomady.data.cache.jsonSerializer;

import com.kulomady.data.ApplicationTestCase;
import com.kulomady.data.cache.serializer.JsonSerializer;
import com.kulomady.data.entity.response.ProductBean;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JsonSerializerTest extends ApplicationTestCase {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private JsonSerializer jsonSerializer;

  @Before
  public void setUp() {
    jsonSerializer = new JsonSerializer();
  }

  @Test
  public void testSerializeHappyCase() {
    ProductBean productEntityOne = jsonSerializer.deserialize(JSON_RESPONSE);
    String jsonString = jsonSerializer.serialize(productEntityOne);
    ProductBean productEntityTwo = jsonSerializer.deserialize(jsonString);

    assertThat(productEntityOne.getProduct_id(), is(productEntityTwo.getProduct_id()));
    assertThat(productEntityOne.getProduct_name(), is(equalTo(productEntityTwo.getProduct_name())));
  }

  @Test
  public void testDesearializeHappyCase() {
    ProductBean userEntity = jsonSerializer.deserialize(JSON_RESPONSE);

    assertThat(userEntity.getProduct_id(), is(1));
    assertThat(userEntity.getProduct_name(), is("Simon Hill"));

  }
}
