package com.triviapp.datos.form;

import static com.triviapp.aux.FileController.readFile;
import static com.triviapp.aux.FileController.saveFile;
import static com.triviapp.aux.FileController.verifyFile;
import com.triviapp.datos.StorageFileAnalyzer;
import com.triviapp.generator.Generator;
import com.triviapp.generator.form.RecopiledDataGenerator;
import com.triviapp.modelo.DatoRecopilado;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author vicente
 */
public class DatoRecopiladoDAO {
    private final String NAME_USER_LINUX = System.getProperty("user.name");
    private final String PATH_FORMS = "/home/" + NAME_USER_LINUX + "/NetBeansProjects/TriviApp/forms/";
    private final StorageFileAnalyzer analyzer = new StorageFileAnalyzer();
    private Generator datoRSG;

    public void create(List<DatoRecopilado> t, String idForm) {
        datoRSG = new RecopiledDataGenerator(t);
        saveFile(PATH_FORMS + idForm + "/recopiledData.db" , datoRSG.generate());
    }

    public List<DatoRecopilado> getObject(String idForm) {
        StringReader text = new StringReader(readFile(PATH_FORMS + idForm + "/recopiledData.db"));
        return analyzer.analyzeData(text);
    }
    
    public boolean exists(String idForm) {
        return verifyFile(PATH_FORMS + idForm + "/recopiledData.db");
    }
}
