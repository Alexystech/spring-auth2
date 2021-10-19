package com.slasher.springauth2.util;

import io.vavr.control.Try;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtils {

  @Value("${jms.jwt.timezone}")
  private String TIMEZONE;


  private SimpleDateFormat simpleDateFormat() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
    return sdf;
  }

  public String getDateString() {
    Date now = new Date();
    return simpleDateFormat().format(now);
  }

  public long getDateMillis() {
    Date now = new Date();
    String strDate = simpleDateFormat().format(now);
    Date strNow = toDate(strDate).get();

    return strNow.getTime();
  }

  private Try<Date> toDate(String strDate) {
    return Try.of( () -> simpleDateFormat().parse(strDate))
        .onFailure(Throwable::printStackTrace);
  }

}
