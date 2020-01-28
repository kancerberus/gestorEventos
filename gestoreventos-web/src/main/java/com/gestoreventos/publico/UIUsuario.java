/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import com.gestoreventos.controller.GestorGeneral;
import com.gestoreventos.entity.App;
import com.gestoreventos.entity.Dialogo;
import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.modelo.Sesion;
import com.gestoreventos.publico.controlador.GestorConfiguracion;
import com.gestoreventos.publico.controlador.GestorLista;
import com.gestoreventos.publico.controlador.GestorUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiUsuario")
@SessionScoped

public class UIUsuario {

    public static final String DIALOGO_CREAR = "dialogoNuevoUsuario";
    public static final String COMPONENTES_REFRESCAR = "formNuevoUsuario";
    private List<Roles> itemsRoles;    
    private String usuarioBuscar;
    private String usuario;
    private String clave;
    
    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;

    public void cancelar() {
    }

    public UIUsuario() {        
        this.itemsRoles = new ArrayList<>();
        this.cargarRoles();
        

    }

    public String getDialogoCrearNuevo() {
        return DIALOGO_CREAR;
    }

    public String getComponentesRefrescar() {
        return COMPONENTES_REFRESCAR;
    }

    public String registrarEstablecimiento() {
        return ("/registro.xhtml?faces-redirect=true");
    }

    /**
     * Cargar los datos del usuario.
     *
     * @param establecimiento
     * @param usuario
     *
     */
    private Usuarios cargarDatosUsuario( Usuarios usuario) throws Exception {
        GestorUsuario gestorUsuario = new GestorUsuario();
        return gestorUsuario.cargarDatosUsuario(usuario, App.USUARIOS_FILTRO_USUARIO);
    }

    /**
     * Cargar los datos del usuario.
     *
     * @param establecimiento
     * @param usuario
     *
     */
    /*private Usuarios cargarDatosUsuario(Usuarios usuario) throws Exception {
        GestorUsuario gestorUsuario = new GestorUsuario();
        return gestorUsuario.cargarDatosUsuario(usuario, App.USUARIOS_FILTRO_USUARIO);
    }*/

    
    public String validarUsuario() throws Exception {
        final Sesion sesion = new Sesion();
        Usuarios usuarios = new Usuarios();
        
        boolean usuarioValido;
        
        try {
            
            GestorUsuario gestorUsuario = new GestorUsuario();            
            GestorConfiguracion gestorConfiguracion = new GestorConfiguracion();
            GestorGeneral gestorGeneral = new GestorGeneral();            
            GestorLista gestorLista = new GestorLista();
                

            usuarios.setUsuario(usuario);
            usuarios.setClave(clave);
            

            usuarioValido = gestorUsuario.validarUsuario(usuarios.getUsuario(), usuarios.getClave());
            if (usuarioValido) {

                usuarios = gestorUsuario.cargarDatosUsuario(usuarios, App.USUARIOS_FILTRO_USUARIO);
                
                
            } if(usuarios.getRoles().getCodigoRol()==1) {

                sesion.setUsuarios((Usuarios) usuarios.clone());
                
                //sesion.setConfiguracion(gestorConfiguracion.cargarConfiguracion());
                
                usuarios = new Usuarios();
                UtilJSF.setBean("usuarios", usuarios, UtilJSF.SESSION_SCOPE);
                UtilJSF.setBean("sesion", sesion, UtilJSF.SESSION_SCOPE);

                UtilJSF.setBean("dialogo", new Dialogo(), UtilJSF.SESSION_SCOPE);
                
                return ("/inicio/principal.xhtml?faces-redirect=true");
            }else{
                UtilMSG.addWarningMsg("Usuario o clave invalida.");
            }
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilLog.generarLog(this.getClass(), ex);
                UtilMSG.addErrorMsg("errorPersistencia");
            }
        }
        return "";
    }

    public List<String> autoCompletaUsuario(String query) {
        List<String> resultado = new ArrayList<>();
        try {            
            GestorUsuario gestorUsuario = new GestorUsuario();
            
            resultado.addAll(gestorUsuario.autoCompletaUsuario( query));
                
        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al consultar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }
        return resultado;
    }

    public void cargarUsuario() {

        try {
            Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
            
            if (usuarioBuscar == null || !usuarioBuscar.contains("-")) {
                UtilMSG.addWarningMsg("Usuario no encontrado.");
            }
            usuario.setUsuario(usuarioBuscar.split("-")[0].trim());
            usuario = this.cargarDatosUsuario( usuario);
            for (Roles r : itemsRoles) {
                if (usuario.getRoles() != null && usuario.getRoles().getCodigoRol() == r.getCodigoRol()) {
                    usuario.setRoles(r);
                }
            }

            UtilJSF.setBean("usuarios", usuario, UtilJSF.SESSION_SCOPE);
            this.guardarActivo = true;
        } catch (Exception ex) {
            UtilMSG.addErrorMsg("Error al cargar el usuario");
            UtilLog.generarLog(this.getClass(), ex);
        }

    }

    public List<?> removerElementosAsignados(List<?> disponibles, List<?> asignados) {
        CopyOnWriteArrayList origen = new CopyOnWriteArrayList(disponibles);
        CopyOnWriteArrayList destino = new CopyOnWriteArrayList(asignados);
        for (Object obj1 : origen) {
            for (Object obj2 : destino) {
                if (obj1.equals(obj2)) {
                    origen.remove(obj2);
                }
            }
        }
        return new ArrayList(origen);
    }

    public String cerrarSesion() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) externalContext.getSession(true)).invalidate();
        return ("/ingreso.xhtml?faces-redirect=true");
    }
    

    public String volverUsuario() {
        return ("/usuario/usuarios.xhtml?faces-redirect=false");
    }

    public String nuevo() {
        UtilJSF.setBean("usuarios", new Usuarios(), UtilJSF.SESSION_SCOPE);
        return ("/usuario/usuarios-nuevo.xhtml?faces-redirect=false");
    }

    public void nuevoCrear() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
        
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            if (gestorUsuario.existeDocumentoUsuario(usuario)) {
                throw new Exception("El documento de identificación ya existe por favor valide.", UtilLog.TW_VALIDACION);
            }
            if (gestorUsuario.existeUsuario(usuario)) {
                throw new Exception("El usuario ya existe por favor valide.", UtilLog.TW_VALIDACION);
            }
            usuario = gestorUsuario.validarUsuarioNuevo(usuario);
            gestorUsuario.almacenarUsuario( usuario);
            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuarios", usuario, UtilJSF.SESSION_SCOPE);
            UtilMSG.addSuccessMsg("Usuario creado");
            guardarActivo = false;
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addErrorMsg("Ocurrio una excepción al crear el usuario");
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void guardar() {
        Usuarios usuario = (Usuarios) UtilJSF.getBean("usuarios");
        
        try {
            if (usuario.getRoles() == null || usuario.getRoles().getCodigoRol() == null
                    || usuario.getRoles().getCodigoRol() == 0) {
                UtilMSG.addWarningMsg("Por favor seleccione el rol del usuario.");
                return;
            }

            GestorUsuario gestorUsuario = new GestorUsuario();
            

            gestorUsuario.almacenarUsuario( usuario);

            gestorUsuario.almacenarRolUsuario( usuario);

            usuario = new Usuarios();
            this.usuarioBuscar = null;
            UtilJSF.setBean("usuarios", usuario, UtilJSF.SESSION_SCOPE);
            UtilMSG.addSuccessMsg("Usuario modificado correctamente");
            guardarActivo = false;
        } catch (Exception ex) {
            if (UtilLog.causaControlada(ex)) {
                UtilMSG.addWarningMsg(ex.getMessage());
            } else {
                UtilMSG.addErrorMsg("Ocurrio una excepción al modificar usuario");
                UtilLog.generarLog(this.getClass(), ex);
            }
        }
    }

    public void eliminar() {
        UtilMSG.addWarningMsg("No se permite eliminar usuario, por favor inactivelo.");
    }

    /**
     * @return the itemsRoles
     */
    public List<Roles> getItemsRoles() {
        return itemsRoles;
    }

    /**
     * @param itemsRoles the itemsRoles to set
     */
    public void setItemsRoles(List<Roles> itemsRoles) {
        this.itemsRoles = itemsRoles;
    }

    private void cargarRoles() {
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            this.itemsRoles = gestorUsuario.cargarRoles();
        } catch (Exception ex) {

        }
    }


    /**
     * @return the usuarioBuscar
     */
    public String getUsuarioBuscar() {
        return usuarioBuscar;
    }

    /**
     * @param usuarioBuscar the usuarioBuscar to set
     */
    public void setUsuarioBuscar(String usuarioBuscar) {
        this.usuarioBuscar = usuarioBuscar;
    }    

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    public boolean isVolverActivo() {
        return volverActivo;
    }

    public void setVolverActivo(boolean volverActivo) {
        this.volverActivo = volverActivo;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the establecimiento
     */
    

    /**
     * @return the guardarActivo
     */
    public boolean isGuardarActivo() {
        return guardarActivo;
    }

    /**
     * @param guardarActivo the guardarActivo to set
     */
    public void setGuardarActivo(boolean guardarActivo) {
        this.guardarActivo = guardarActivo;
    }

    /**
     * @return the nuevoActivo
     */
    public boolean isNuevoActivo() {
        return nuevoActivo;
    }

    /**
     * @param nuevoActivo the nuevoActivo to set
     */
    public void setNuevoActivo(boolean nuevoActivo) {
        this.nuevoActivo = nuevoActivo;
    }

    /**
     * @return the eliminarActivo
     */
    public boolean isEliminarActivo() {
        return eliminarActivo;
    }

    /**
     * @param eliminarActivo the eliminarActivo to set
     */
    public void setEliminarActivo(boolean eliminarActivo) {
        this.eliminarActivo = eliminarActivo;
    }

    /**
     * @return the consultarActivo
     */
    public boolean isConsultarActivo() {
        return consultarActivo;
    }

    /**
     * @param consultarActivo the consultarActivo to set
     */
    public void setConsultarActivo(boolean consultarActivo) {
        this.consultarActivo = consultarActivo;
    }

    /**
     * @return the cancelarActivo
     */
    public boolean isCancelarActivo() {
        return cancelarActivo;
    }

    /**
     * @param cancelarActivo the cancelarActivo to set
     */
    public void setCancelarActivo(boolean cancelarActivo) {
        this.cancelarActivo = cancelarActivo;
    }

}
