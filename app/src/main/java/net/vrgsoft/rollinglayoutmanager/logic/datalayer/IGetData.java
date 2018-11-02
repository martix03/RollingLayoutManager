package net.vrgsoft.rollinglayoutmanager.logic.datalayer;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public interface IGetData {
    void getRepoDownloaded(List<Repo> lista);
    void elementi_non_disponibili();
    void errore();
}
