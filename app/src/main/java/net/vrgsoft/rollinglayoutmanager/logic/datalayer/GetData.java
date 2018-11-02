package net.vrgsoft.rollinglayoutmanager.logic.datalayer;

import android.content.Context;
import android.database.Cursor;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.database.GetDataDB;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.GetDataRest;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.IGetRepo;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.ArrayList;
import java.util.List;

public class GetData implements IGetData {
    static GetDataDB db;
    static IGetRepo listenerRepo;
    List<Repo> list_repo;
    String nickname;

    public void creazioneDB(Context c) {
        db = new GetDataDB(c);
    }



    public void getRepo(String n, final IGetRepo l) {
        if (!n.equals(""))
            nickname = n;
        listenerRepo = l;
        list_repo = new ArrayList<>();
        db.open();
        Cursor lista_report_cursor = db.ottieniRepo(nickname);

        if (lista_report_cursor != null && lista_report_cursor.getCount() > 0) {
            if (lista_report_cursor.moveToFirst()) {
                do {
                    Repo repo = new Repo();
                    repo.setId(Long.valueOf(lista_report_cursor.getString(lista_report_cursor.getColumnIndex(GetDataDB.KEY_REPO_IDGITHUB))));
                    repo.setName(lista_report_cursor.getString(lista_report_cursor.getColumnIndex(GetDataDB.KEY_REPO_NAME)));
                    repo.setLanguage(lista_report_cursor.getString(lista_report_cursor.getColumnIndex(GetDataDB.KEY_REPO_LANGUAGE)));
//                    Owner o = new Owner();
//                    o.setLogin(lista_report_cursor.getString(lista_report_cursor.getColumnIndex(GetDataDB.KEY_REPO_NICK_OWNER)));
//                    repo.setOwner(o);
//                    //Log.e("DB", "Estrazione dal DB REPO " + lista_report_cursor.getString(lista_report_cursor.getColumnIndex(GetDataDB.KEY_REPO_NAME)));
                    list_repo.add(repo);
                } while (lista_report_cursor.moveToNext());
            }

            listenerRepo.listRepo(list_repo);
        } else {
            GetDataRest.downloadRepository(nickname, this);
        }
        db.close();
    }

    public List<Repo> getRepoLocal() {
        return list_repo;
    }


    @Override
    public void getRepoDownloaded(List<Repo> lista) {
        listenerRepo.listRepo(lista);
        list_repo = lista;
        db.open();
        //Log.e("DB", "Inserimento nel DB REPO");
        for (int i = 0; i < lista.size(); i++) {
            try {
                db.inserisciRepo(lista.get(i).getName(), lista.get(i).getLanguage().toString(), String.valueOf(lista.get(i).getId()), String.valueOf(lista.get(i).getOwner().getId()), lista.get(i).getOwner().getLogin());
            } catch (Exception e) {
                db.inserisciRepo(lista.get(i).getName(), "Undefined", String.valueOf(lista.get(i).getId()), String.valueOf(lista.get(i).getOwner().getId()), lista.get(i).getOwner().getLogin());
            }
        }
        db.close();
    }

    @Override
    public void elementi_non_disponibili() {
        listenerRepo.repo_non_disponibili();
    }

    @Override
    public void errore() {
        listenerRepo.errore();
    }
}
