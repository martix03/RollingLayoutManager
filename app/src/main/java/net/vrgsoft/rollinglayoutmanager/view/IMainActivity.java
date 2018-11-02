package net.vrgsoft.rollinglayoutmanager.view;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public interface IMainActivity {
    void repo_presenti(List<Repo> list);
    void repo_non_presenti();
    void errore();
}