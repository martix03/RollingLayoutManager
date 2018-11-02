package net.vrgsoft.rollinglayoutmanager.logic;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public interface ILogic {

    void repo_disponibili(List<Repo> lista);
    void repo_non_disponibili();
    void errore();

}
