/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenproject.controlador;

import com.mavenproject.EJB.UsuarioFacadeLocal;
import com.mavenproject.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jaime
 */
@Named
@ViewScoped
public class RegistroController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();

    }
    public void registrar(){
        boolean nickExistente;
        
        
        try{
            nickExistente = usuarioEJB.nickExistente(this.usuario);
            if(nickExistente == false){
                this.usuario.setTipo("user"); 
                usuarioEJB.create(this.usuario);
            }else{
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre de usuario repetido", "Por favor, elija un nick diferente"));
                
                //mensaje por pantalla
            }
           
            
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario registrado con éxito"));
        }catch(Exception e){}
    }
    
     public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
