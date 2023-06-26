package org.tnmk.tech_common.csv;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tnmk.tech_common.csv.test_support.Continent;

import java.util.List;

class SimpleCsvReaderTest {
  final SimpleCsvReader simpleCsvReader = new SimpleCsvReader();

  @Test
  void readCsvWithHeadersLineToBean() {
    // GIVEN
    String content =
      "name,m49Code,continent\n" +
        "aaa,1,Africa\n" +
        "bbb,2,Americas\n" +
        "ccc,3,Europe\n";
    // WHEN
    List<SampleModel> regions = simpleCsvReader.readCsvWithHeadersLineToBean(content, ",", SampleModel.class);

    // THEN
    Assertions.assertEquals(3, regions.size());

    Assertions.assertEquals("aaa", regions.get(0).getName());
    Assertions.assertEquals(1, regions.get(0).getM49Code());
    Assertions.assertEquals(Continent.Africa, regions.get(0).getContinent());

    Assertions.assertEquals("bbb", regions.get(1).getName());
    Assertions.assertEquals(2, regions.get(1).getM49Code());
    Assertions.assertEquals(Continent.Americas, regions.get(1).getContinent());

    Assertions.assertEquals("ccc", regions.get(2).getName());
    Assertions.assertEquals(3, regions.get(2).getM49Code());
    Assertions.assertEquals(Continent.Europe, regions.get(2).getContinent());
  }

  @Getter
  @Setter
  public static class SampleModel {
    private String name;

    private int m49Code;

    private Continent continent;
  }
}