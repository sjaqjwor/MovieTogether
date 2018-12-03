package com.movie.movie.utils.naver;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class NaverSearchData {
    List<Map<String,String>> items;
}
