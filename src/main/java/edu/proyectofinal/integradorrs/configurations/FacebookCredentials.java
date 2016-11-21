package edu.proyectofinal.integradorrs.configurations;

import org.springframework.web.client.RestTemplate;

import edu.proyectofinal.integradorrs.model.Token;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.springframework.social.facebook.api.Facebook;

/**
 *
 * @author Emi
 */
public class FacebookCredentials {
   
   //private UsuarioService usuarioService;
   private static FacebookCredentials credentials = null;

   private FacebookCredentials() { }
   private String appID = "1284910861551517";
   private String appSecret = "b2c5ac1498b60dc145c1e2a51c5a9027";
   private String OAuthAccessToken;
           
   public static FacebookCredentials getInstance( ) {
      if(credentials == null)
      {
          credentials = new FacebookCredentials();
      }
       return credentials;
   }

   public facebook4j.Facebook SetCredentials(String email)
   {   

    this.OAuthAccessToken = GetToken(email).getToken();

    facebook4j.Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId(this.appID,this.appSecret);
    String accessTokenString = this.OAuthAccessToken;
    AccessToken at = new AccessToken(accessTokenString);
    facebook.setOAuthAccessToken(at);
      
      return facebook;
   }

   public Token GetToken(String email)
   {
      RestTemplate restTemplate = new RestTemplate();
      Token atoken = restTemplate.getForObject("http://localhost:8080/api/usuario/token/fb/email/"+email,Token.class);
      return atoken;
   }
}