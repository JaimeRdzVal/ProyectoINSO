/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenproject.EJB;

import com.mavenproject.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jaime
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    Usuario us = null;
    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public boolean nickExistente(Usuario user){
        String consulta1 = null;
        consulta1="SELECT u FROM Usuario u WHERE u.nick = ?1";
        Query query = em.createQuery(consulta1);
        query.setParameter(1, user.getNick());
        List<Usuario> listaUsuarios = query.getResultList();
           System.out.println("Está haciendo la comprobación");
            if(listaUsuarios.isEmpty()){       
                    System.out.println("Está todo ok");
                return false;
         
            }
            else{
                 System.out.println("Está todo mal");
                
                return true;   
            }  
    }

}
