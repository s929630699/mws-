package utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mws.pojo.User;

//定义一个用于进行Token处理的工具类，提供token生成、验证等方法
@Component
public class JwtTokenUtil implements Serializable {
 
    private static final long serialVersionUID = -3301605591108950415L;
 
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
 
    @Value("${jwt.secret}")//读取配置中的信息赋值给属性
    private String secret;
 
    @Value("${jwt.expiration}")
    private String expiration;
 
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
 
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }
 
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
 
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
 
    private Date generateExpirationDate() {
    	System.out.println("1"+System.currentTimeMillis());
    	System.out.println("2"+this.expiration);
        return new Date(System.currentTimeMillis() +60 *60*60);
    }
 
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
 
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
 
    public String generateToken(User userInfo) {
        Map<String, Object> claims = new HashMap<String, Object>();
        System.out.println("5"+userInfo.getUsername());
        claims.put(CLAIM_KEY_USERNAME, userInfo.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
 //根据JWT方案生成Token
    String generateToken(Map<String, Object> claims) {
    	System.out.println("4"+secret);
    	System.out.println("3"+Jwts.builder()
        .setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
 
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }
 
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
 
    public Boolean validateToken(String token, User userinfo) {
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
//        final Date expiration = getExpirationDateFromToken(token);
         boolean a=username.equals(userinfo.getUsername());
         boolean b=isTokenExpired(token);
         String date=userinfo.getRegistTime();
         SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date registime=null;
		try {
			registime = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean c=isCreatedBeforeLastPasswordReset(created, registime);
        return (
                username.equals(userinfo.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, registime));
    }
}