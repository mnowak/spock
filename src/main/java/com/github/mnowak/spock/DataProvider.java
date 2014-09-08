package com.github.mnowak.spock;

import java.util.Date;
import java.util.List;

public interface DataProvider {

    List<String> data();

    List<String> pagedData(Integer page, Integer pageSize);

    Integer size();

    Date updatedAt();

    Person responsible();
}
