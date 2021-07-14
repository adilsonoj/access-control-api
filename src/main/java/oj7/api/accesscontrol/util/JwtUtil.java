package oj7.api.accesscontrol.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import oj7.api.accesscontrol.model.Pessoa;

public class JwtUtil {
  private static final String SECRET_KEY = "482d648a75fc3d81f9e30a01aa3e9a6a9572b05e9347eafef034a0728b676b5a";
  public static String createJwt(){
    String id = "001";
    String issuer = "problema";
    String subject = "subject";
    long ttlMillis = 200000;
    
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    Pessoa pessoa = Pessoa.builder()
                    .nrNip(524666L)
                    .build();
    JwtBuilder jwtBuilder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer(issuer)
            .claim("pessoa", pessoa)
            .signWith(signingKey);
    
    
    if (ttlMillis > 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      jwtBuilder.setExpiration(exp);
    }  

    return jwtBuilder.compact();
  }

  public static Claims decodeJWT(String jwt) {
    Jws<Claims> parseClaimsJws = Jwts.parserBuilder()
                      .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                      .build()
                      .parseClaimsJws(jwt);
                      System.out.println(parseClaimsJws.getHeader());
    return parseClaimsJws.getBody();
}

public static void main(String[] args) {
  String createJwt = createJwt();
  System.out.println("encode");
  System.out.println(createJwt);
  System.out.println("decode");
  System.out.println(decodeJWT(createJwt));

}
  
}
