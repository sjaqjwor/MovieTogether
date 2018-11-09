package com.movie.movie.utils.restobject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class KoficObject {
    private String boxofficeType;
    private String showRange;
    private List<Map<String,String>> dailyBoxOfficeList;
}
