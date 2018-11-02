package net.vrgsoft.rollinglayoutmanager.logic;

import android.content.Context;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.GetData;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.IGetRepo;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public class Logic implements IGetRepo {
    ILogic listenerLogic;
    public static GetData getData;

    public void getRepository(String nickname, ILogic listener, Context c) {
        listenerLogic = listener;
        getData = new GetData();
        getData.creazioneDB(c);
        getData.getRepo(nickname, this);

    }



    @Override
    public void listRepo(List<Repo> lista) {
        if (lista != null) {
            listenerLogic.repo_disponibili(lista);
        } else {
            listenerLogic.repo_non_disponibili();
        }
    }

    @Override
    public void repo_non_disponibili() {

    }

    @Override
    public void errore() {

    }
}
