package com.slasher.springauth2.security;

import com.slasher.springauth2.exceptions.TokenExpiredException;
import com.slasher.springauth2.util.GsonUtils;
import io.fusionauth.jwt.JWTExpiredException;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.TimeZone;

@Component
public class JwtIO {

  @Value("${jms.jwt.token.secret:secret}")
  private String SECRET;
  @Value("${jms.jwt.timezone:UTC}")
  private String TIMEZONE;
  @Value("${jms.jwt.token.expires-in:3600}")
  private int EXPIRES_IN;
  @Value("${jms.jwt.issuer:none}")
  private String ISSUER;

  public String generateToken(Object src) {

    //serializacion del sujeto
    String subject = GsonUtils.serialize(src);

    //construccion de la firma con el codigo secreto
    Signer signer = HMACSigner.newSHA256Signer(SECRET);

    //construccion de la fecha de expiracion
    TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE);
    ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone.toZoneId())
        .plusSeconds(EXPIRES_IN);

    //construccion del jwt
    JWT jwt = new JWT().setIssuer(ISSUER)  //entidad que esta generando el token
        .setIssuedAt(ZonedDateTime.now(timeZone.toZoneId())) //fecha en que se solicita
        .setSubject(subject)  // se agrega el subject al payload o data
        .setExpiration(zonedDateTime);  //se agrega la expiracion del token

    return JWT.getEncoder().encode(jwt, signer); // se retorna el jwt encriptado (aqui es donde se encripta)
  }

  /**
   * se valida si el token ha expirado o no
   * @param encodedJWT
   * @return
   */
  public boolean validateToken(String encodedJWT) {

    boolean validate = false;

    try {
      //desencriptamos el jwt
      JWT jwt = jwt(encodedJWT);

      //se valida que el jwt no haya expirado
      validate = jwt.isExpired();
    } catch (JWTExpiredException e) {
      validate = true;
    }

    //comprobamos que el jwt ha expirado o no
    return validate;
  }

  /**
   * se retorna el subject del payload
   * @param encodedJWT
   * @return
   */
  public String getPayload(String encodedJWT) {

    //desencriptamos el jwt
    JWT jwt = jwt(encodedJWT);

    //obtenemos el subject y lo retornamos
    return jwt.subject;
  }

  /**
   * permite desencriptar y retornar el jwt
   * @param encodedJWT
   * @return
   */
  private JWT jwt(String encodedJWT) {

    //verifica la firma del jwt
    Verifier verifier = HMACVerifier.newVerifier(SECRET);

    return JWT.getDecoder().decode(encodedJWT, verifier);
  }

}
