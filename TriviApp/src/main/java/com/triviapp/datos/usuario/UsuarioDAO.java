package com.triviapp.datos.usuario;

import static com.triviapp.aux.FileController.*;

import com.triviapp.generator.Generator;
import com.triviapp.modelo.Usuario;
import com.triviapp.datos.CRUD;
import com.triviapp.datos.StorageFileAnalyzer;
import com.triviapp.generator.user.UserStorageStructureGenerator;
import java.io.StringReader;
import java.util.List;



/**
 *
 * @author vicente
 */
public class UsuarioDAO implements CRUD<Usuario>{
    private final String NAME_USER_LINUX = System.getProperty("user.name");
    private final String PATH_USERS = "/home/" + NAME_USER_LINUX + "/NetBeansProjects/TriviApp/";
    private final StorageFileAnalyzer analyzer = new StorageFileAnalyzer();
    private Generator userSSG;

    @Override
    public List<Usuario> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Usuario u) {
        userSSG = new UserStorageStructureGenerator(u);
        createDirectory(PATH_USERS);
        saveFile(PATH_USERS + u.getNombre() + ".db", userSSG.generate());
    }

    @Override
    public Usuario getObject(String id) {
        StringReader text = new StringReader(readFile(PATH_USERS + id + ".db"));
        return analyzer.analyzeUser(text);
    }

    @Override
    public boolean update(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String path) {
        return deleteFile(PATH_USERS + path + ".db");
    }

    @Override
    public boolean exists(String id) {
        return verifyFile(PATH_USERS + id + ".db");
    }
}
