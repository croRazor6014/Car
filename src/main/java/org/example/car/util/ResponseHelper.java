package org.example.car.util;

import java.util.UUID;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * Created by lovro.vrlec on Dec,2018
 */
@Slf4j
@UtilityClass
public class ResponseHelper {


  /**
   * @param t            t
   * @param functionName name
   * @param <T>          t
   * @return ResponseEntity
   */
  public static <T> ResponseEntity<T> responseHttpOkAndLog(final T t, final String functionName) {
    return responseHttpOkAndLog(t, functionName, null);
  }

  /**
   * @param t   t
   * @param <T> t
   * @return ResponseEntity
   */
  public static <T> ResponseEntity<T> responseHttpOk(final T t) {
    return ResponseEntity.ok().body(t);
  }


  /**
   * @param t               t
   * @param serializedInput input
   * @param <T>             t
   * @return ResponseEntity
   */
  public static <T> ResponseEntity<T> responseHttpOk(final T t,
                                                     final String serializedInput) {

    log.info(" || " + serializedInput);
    return ResponseEntity.ok().body(t);
  }

  /**
   * @param t               t
   * @param functionName    name
   * @param serializedInput input
   * @param <T>             t
   * @return ResponseEntity
   */
  public static <T> ResponseEntity<T> responseHttpOkAndLog(final T t, final String functionName,
                                                           final String serializedInput) {
    final UUID uuid = UUID.randomUUID();

    log.info(uuid + " || " + functionName + " || " + serializedInput);
    return ResponseEntity.ok().header("uuid", uuid.toString()).body(t);
  }

}
