package net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public interface IGetRepo {

    void listRepo(List<Repo> lista);

    void repo_non_disponibili();

    void errore();

}
