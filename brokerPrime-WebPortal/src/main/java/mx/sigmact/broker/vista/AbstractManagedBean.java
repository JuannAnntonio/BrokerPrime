package mx.sigmact.broker.vista;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.sigmact.broker.model.dto.User;

import org.apache.log4j.Logger;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public abstract class AbstractManagedBean implements Serializable {

    private static final long serialVersionUID = -954717909755577329L;

    /**
     * Objeto del log.
     */
    private final Logger log;
    private HttpSession session;
    private User user;

    /**
     * The constant factory.
     */
    private final PodamFactory factory = new PodamFactoryImpl();

    public static final String ERROR = "Error";
    public static final String INFO = "Información";
    public static final String WARN = "Atención";
    public static final String FATAL = "Error grave";

    /**
     * pistaAuditoriaService es la referencia del servicio de Pistas de
     * Auditoria.
     */
    public AbstractManagedBean() {
        log = Logger.getLogger(getClass());
    }
    
    protected void validateUsuarioValido() throws IOException {
//        if (getUserProfile() != null) {
//            usuario = getUserProfile();
//        } else {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(
//                    ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getContextPath()
//                    + "/error/indexError.jsf");
//        }
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

   public HttpSession getSession() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session;
    }
    
    public HttpSession getNewSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public void despachaArchivo(byte[] archivo, String contentType, String nombreArchivo, String error) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                    getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setContentLength(archivo.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

            ServletOutputStream out;
            out = response.getOutputStream();
            out.write(archivo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException e) {
            getLogger().error(e.getMessage());
            addFatalMessage(FacesMessage.SEVERITY_FATAL.toString(),error);
        }

    }

    private void createMessage(String summary, String detail,
            FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_INFO);
    }

    public void addErrorMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_ERROR);
    }

    public void addFatalMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_FATAL);
    }

    public void addWarningMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_WARN);
    }

   

    /**
     * Obtiene un mensaje del archivo de propiedades de una clave enviada.
     *
     * @param key clave a buscar.
     * @param params uno varios objetos que determinan el formato del mensaje.
     * @return String con el mensaje obtenido.
     */
    public String getMessageResourceString(String key, Object... params) {
        String value;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "tbcMsj");

        value = formatMessage(bundle, key, params);
        return value;
    }

    private static String formatMessage(ResourceBundle bundle, String key, Object... params) {
        String text;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        }
        return text;
    }

    public String getRequestURL() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest) request).getRequestURL().toString();
        } else {
            return "";
        }
    }

    public Logger getLogger() {
        return log;
    }

    public PodamFactory getFactory() {
        return factory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
