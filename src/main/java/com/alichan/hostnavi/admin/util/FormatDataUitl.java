package com.alichan.hostnavi.admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FormatDataUitl {
  public static List<Long> convertStringIdsToLongArray(String ids) {
    List<String> longIds = Arrays.asList(ids.split(","));
    return longIds.stream().map(id -> Long.parseLong(id)).collect(Collectors.toList());
  }

  public static List<Integer> convertStringIdsToIntegerArray(String ids) {
    List<String> intIds = Arrays.asList(ids.split(","));
    return intIds.stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList());
  }

  public static Date convertStringDateToDate(String date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      return format.parse(date);
    } catch (ParseException error) {
      throw new Error();
    }
  }

  public static String convertDateToStringDate(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return format.format(date);
  }
}
