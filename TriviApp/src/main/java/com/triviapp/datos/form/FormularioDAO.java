package com.triviapp.datos.form;

import static com.triviapp.aux.FileController.createDirectory;
import static com.triviapp.aux.FileController.deleteDirectory;
import static com.triviapp.aux.FileController.getfileNames;
import static com.triviapp.aux.FileController.readFile;
import static com.triviapp.aux.FileController.saveFile;
import static com.triviapp.aux.FileController.verifyFile;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.StorageFileAnalyzer;
import com.triviapp.generator.Generator;
import com.triviapp.generator.form.FormStorageStructureGenerator;
import com.triviapp.modelo.Formulario;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicente
 */
public class FormularioDAO implements CRUD <Formulario> {
    
    private final String NAME_USER_LINUX = System.getProperty("user.name");
    private final String PATH_FORMS = "/home/" + NAME_USER_LINUX + "/NetBeansProjects/TriviApp/data/trivias/";
    private final StorageFileAnalyzer analyzer = new StorageFileAnalyzer();
    private Generator formSSG;

    @Override
    public List<Formulario> getList() {
        List<String> fileNames = getfileNames(PATH_FORMS);
        List<Formulario> forms = new ArrayList();

        fileNames.forEach(fn -> forms.add(getObject(fn)));

        return forms;
    }

    @Override
    public void create(Formulario f) {
        formSSG = new FormStorageStructureGenerator(f);
        createDirectory(PATH_FORMS + f.getId());
        saveFile(PATH_FORMS + f.getId() + "/estructura.db", formSSG.generate());
    }

    @Override
    public Formulario getObject(String id) {
        StringReader text = new StringReader(readFile(PATH_FORMS + id + "/estructura.db"));
        return analyzer.analyzeForm(text);
    }

    @Override
    public boolean update(Formulario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        return deleteDirectory(new File(PATH_FORMS + id));
    }

    @Override
    public boolean exists(String id) {
        return verifyFile(PATH_FORMS + id);
    }
    
}
