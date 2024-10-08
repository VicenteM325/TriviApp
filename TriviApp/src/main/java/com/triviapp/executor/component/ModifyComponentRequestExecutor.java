package com.triviapp.executor.component;

import com.triviapp.aux.component.ComponentBuilder;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.form.FormularioDAO;
import com.triviapp.executor.Executor;
import com.triviapp.modelo.Componente;
import com.triviapp.modelo.Formulario;
import com.triviapp.modelo.solicitudes.Solicitud;

/**
 *
 * @author vicente
 */
public class ModifyComponentRequestExecutor extends Executor {

    private final CRUD<Formulario> formDAO;
    private ComponentBuilder componentBuilder;

    public ModifyComponentRequestExecutor() {
        formDAO = new FormularioDAO();
    }

    @Override
    public String execute(Solicitud s) {
        response = new StringBuilder();

        componentBuilder = new ComponentBuilder(s);
        var comp = componentBuilder.build();
        
        if (formDAO.exists(comp.getFormulario())) {
            var form = formDAO.getObject(comp.getFormulario());
            var componentes = form.getComponentes();
            int index = indexOfComponent(form, comp.getId());
            
            if (index != -1) {
                boolean canCreated = true;
              //  boolean hasFieldName = comp.getNombreCampo()!= null;
                boolean hasVisibleText = comp.getTextoVisible()!= null;
                boolean hasRespuesta = comp.getRespuesta()!= null;
                boolean hasOptions = comp.getOpciones() != null;
                boolean hasRows = comp.getNoFilas()!= null;
                boolean hasColumns = comp.getNoColumnas()!= null;
                var compForm = componentes.get(index);

                if (comp.getClase() != null) {
                 //   if (hasFieldName) compForm.setNombreCampo(comp.getNombreCampo());
                    if (hasVisibleText) compForm.setTextoVisible(comp.getTextoVisible());
                    if (hasRespuesta) compForm.setRespuesta(comp.getRespuesta());
                    if (hasOptions) compForm.setOpciones(comp.getOpciones());
                    if (hasRows) compForm.setNoFilas(comp.getNoFilas());
                    if (hasColumns) compForm.setNoColumnas(comp.getNoColumnas());
                    compForm.setClase(comp.getClase());
                    System.out.println("Nuevo componente");
                } else {
                    
                    switch (compForm.getClase()) {
                        case "CAMPO_TEXTO", "FICHERO" -> {
                            if (hasOptions | hasRows | hasColumns ) {
                                canCreated = false;
                                addResponse("No se puede modificar el componente " + compForm.getId() + " se ingresaron parametros que no coinciden con la clase " + compForm.getClase());
                            }
                        }
                        
                        case "CHECKBOX", "RADIO", "COMBO" -> {
                            if (hasRows | hasColumns ) {
                                canCreated = false;
                                addResponse("No se puede modificar el componente " + compForm.getId() + " se ingresaron parametros que no coinciden con la clase " + compForm.getClase());
                            } else {
                                if (hasOptions) compForm.setOpciones(comp.getOpciones());
                            }
                        }
                        
                        case "AREA_TEXTO" -> {
                            if ( hasOptions) {
                                canCreated = false;
                                addResponse("No se puede modificar el componente " + compForm.getId() + " se ingresaron parametros que no coinciden con la clase " + compForm.getClase());
                            } else {
                                if (hasRows) compForm.setNoFilas(comp.getNoFilas());
                                if (hasColumns) compForm.setNoColumnas(comp.getNoColumnas());
                            }
                        }
                        
                        case "RESPUESTA" -> {
                            if (hasRespuesta) {
                                canCreated = false;
                                addResponse("No se puede modificar el componente " + compForm.getId() + " se ingresaron parametros que no coinciden con la clase " + compForm.getClase());
                            } else {
                                if (hasRespuesta) compForm.setRespuesta(comp.getRespuesta());
                            }
                        }
                    }

                    
                    switch (compForm.getClase()) {
                        case "CAMPO_TEXTO", "FICHERO", "CHECKBOX", "RADIO", "COMBO", "AREA_TEXTO" -> {
                          //  if (hasFieldName) compForm.setNombreCampo(comp.getNombreCampo());
                            if (hasVisibleText) compForm.setTextoVisible(comp.getTextoVisible());
                            
                        }
                    }
                }
                
                if (comp.getIndice() > 0) {
                    int newIndex = comp.getIndice()-1;
                    var compAux = componentes.remove(index);
                    
                    if (newIndex >= componentes.size()) {
                        componentes.add(compAux);
                    } else {
                        componentes.add(newIndex, compAux);
                    }
                }
                
                if (canCreated) {
                    formDAO.create(form);
                    addResponse("Componente " + compForm.getId() + " modificado");
                }
                
            } else {
                addResponse("No existe el componente " + comp.getId() + " en la Trivia " + form.getId());
            }
            
        } else {
            addResponse("La Trivia " + comp.getFormulario() + " no existe");
        }

        return response.toString();
    }
    
    private int indexOfComponent(Formulario form, String idComponent) {
        Componente c;
        for (int i = 0; i < form.getComponentes().size(); i++) {
            c = form.getComponentes().get(i);
            if (c.getId().equals(idComponent)) {
                return i;
            }
        }
        return -1;
    }

}