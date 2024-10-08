package com.triviapp.generator.form;

import com.triviapp.generator.Generator;
import com.triviapp.modelo.DatoRecopilado;
import java.util.List;

/**
 *
 * @author vicente
 */
public class RecopiledDataGenerator extends Generator {

    private final List<DatoRecopilado> dt;

    public RecopiledDataGenerator(List<DatoRecopilado> dt) {
        this.dt = dt;
    }

    @Override
    public String generate() {
    text = new StringBuilder();

    addLine("db.recopiledData(", 0);
    addLine("{", 1);

    dt.forEach(d -> {
        if (d.getNombre().equals("TIEMPO_PREGUNTA")) {
            addLine("\"" + d.getNombre() + "\" : " + d.getValor() + ",", 2);
        } else {
            addLine("\"" + d.getNombre() + "\" : \"" + d.getValor() + "\",", 2);
        }
    });

    text.deleteCharAt(text.lastIndexOf(","));
    addLine("}", 1);
    addLine(")", 0);

    return text.toString();
    }
}