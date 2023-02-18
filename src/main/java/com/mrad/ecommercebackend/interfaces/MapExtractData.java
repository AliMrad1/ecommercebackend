package com.mrad.ecommercebackend.interfaces;

import java.util.List;
import java.util.Map;

public interface MapExtractData<T> {

    List<T> extractDataToList(List<Map<String, Object>> mapList);
}
