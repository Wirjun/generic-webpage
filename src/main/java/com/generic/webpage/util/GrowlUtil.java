package com.generic.webpage.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class GrowlUtil {
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, "Aktion erfolgreich", detail);
    }

    public void showWarn(String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, "Aktion nicht erfolgreich", detail);
    }

    public void showError(String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Aktion nicht erfolgreich", detail);
    }
}
