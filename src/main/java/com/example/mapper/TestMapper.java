package com.example.mapper;

import java.util.List;
import java.util.Map;

public interface TestMapper {
    List<Map> selectAll();
    List<Map> selectPage();
}
