package com.slasher.springauth2.util;

import com.google.gson.Gson;

public class GsonUtils {

  /**
   * serializacion de un objeto a json
   * @param src
   * @return
   */
  public static String serialize(Object src) {
    Gson gson = new Gson();
    return gson.toJson(src);
  }

  /**
   * deserializacion de un json a un ubjeto retornando un generics
   * @param json
   * @param dClass
   * @param <D>
   * @return
   */
  public static <D> D toObject(String json, Class<D> dClass) {
    Gson gson = new Gson();
    return gson.fromJson(json,dClass);
  }

  public static <D> D toObject(Object src, Class<D> dClass) {
    Gson gson = new Gson();
    String srcJson = gson.toJson(src);
    return gson.fromJson(srcJson, dClass);
  }

}
