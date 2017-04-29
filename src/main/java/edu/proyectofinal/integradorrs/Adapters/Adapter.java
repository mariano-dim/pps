/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.Adapters;

import edu.proyectofinal.integradorrs.model.Update;

/**
 *
 * @author ecorsaro
 */
public class Adapter {
    
    public static Update FacebookPostToUpdate(String email,facebook4j.Post aPost)      
    {
        Update anUpdate = new Update();
        if(aPost != null){
           anUpdate.setEmail(email);
           anUpdate.setSocialnetwork("Facebook");
           anUpdate.setTexto(aPost.getMessage());
           anUpdate.setcreationdate(aPost.getCreatedTime());
           anUpdate.setid(aPost.getId());
           if(aPost.getComments().getCount() == null)
           {
            anUpdate.setcomments(0);
           }else{
                anUpdate.setcomments(aPost.getComments().getCount());
           }
           if(aPost.getLikes().getCount() == null)
           {              
               anUpdate.setlikes(0);
           }else{
               anUpdate.setlikes(aPost.getLikes().getCount());
           }
           if(aPost.getSharesCount() == null)
           {
                anUpdate.setshares(0);
           }else
           {
              anUpdate.setshares(aPost.getSharesCount());
           }
        }
        return anUpdate;
    }
    
}
