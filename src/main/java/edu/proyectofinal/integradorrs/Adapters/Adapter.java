/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.Adapters;

import edu.proyectofinal.integradorrs.model.Update;
import twitter4j.Status;

/**
 *
 * @author ecorsaro
 */
public class Adapter {
    
    public static Update FacebookPostToUpdate(String email,facebook4j.Post aStatus)      
    {
        Update anUpdate = new Update();
        if(aStatus != null){
           anUpdate.setEmail(email);
           anUpdate.setSocialnetwork("Facebook");
           anUpdate.setTexto(aStatus.getMessage());
           anUpdate.setcreationdate(aStatus.getCreatedTime());
           anUpdate.setid(aStatus.getId());
           if(aStatus.getComments().getCount() == null)
           {
            anUpdate.setcomments(0);
           }else{
                anUpdate.setcomments(aStatus.getComments().getCount());
           }
           if(aStatus.getLikes().getCount() == null)
           {              
               anUpdate.setlikes(0);
           }else{
               anUpdate.setlikes(aStatus.getLikes().getCount());
           }
           if(aStatus.getSharesCount() == null)
           {
                anUpdate.setshares(0);
           }else
           {
              anUpdate.setshares(aStatus.getSharesCount());
           }
        }
        return anUpdate;
    }

    public static Update TwitterStatusToUpdate(String email, Status aStatus) {
        Update anUpdate = new Update();
        if(aStatus != null){
           anUpdate.setEmail(email);
           anUpdate.setSocialnetwork("Twitter");
           anUpdate.setTexto(aStatus.getText());
           anUpdate.setcreationdate(aStatus.getCreatedAt());
           anUpdate.setid(String.valueOf(aStatus.getId()));
           anUpdate.setcomments(aStatus.getUserMentionEntities().length);
           anUpdate.setlikes(aStatus.getFavoriteCount());
           anUpdate.setshares(aStatus.getRetweetCount());
        }
        return anUpdate;
    }
    
}
