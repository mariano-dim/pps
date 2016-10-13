package edu.proyectofinal.integradorrs.configurations;

import org.springframework.web.client.RestTemplate;

import edu.proyectofinal.integradorrs.model.Token;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Emi
 */
public class TwitterCredentials {
   
   //private UsuarioService usuarioService;
   private static TwitterCredentials credentials = null;

   private TwitterCredentials() { }
   private String OAuthConsumerKey = "ogIANEpF2RkLxvHrzO0Cu5sVi";
   private String OAuthConsumerSecret = "UbOselGMS6lmGQrey3NsjxO231UzWfOG8akh7Nhc6wbbxtBMQE";
   private String OAuthAccessToken;
   private String OAuthAccessTokenSecret;
           
   public static TwitterCredentials getInstance( ) {
      if(credentials == null)
      {
          credentials = new TwitterCredentials();
      }
       return credentials;
   }

   public ConfigurationBuilder GetCredentials(String email)
   {   
       Token token = GetToken(email);
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true)
     .setOAuthConsumerKey(this.OAuthConsumerKey)
     .setOAuthConsumerSecret(this.OAuthConsumerSecret)
     .setOAuthAccessToken(token.getToken())
     .setOAuthAccessTokenSecret(token.getSecret());
      
      return cb;
   }

   public Token GetToken(String email)
   {
      RestTemplate restTemplate = new RestTemplate();
      Token atoken = restTemplate.getForObject("http://localhost:8080/api/usuario/token/twitter/email/"+email+"?socialnetwork=Twitter",Token.class);
      return atoken;
   }
}