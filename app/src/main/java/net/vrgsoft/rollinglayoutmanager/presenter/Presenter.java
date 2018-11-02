package net.vrgsoft.rollinglayoutmanager.presenter;

import android.content.Context;
import android.util.Log;

import net.vrgsoft.rollinglayoutmanager.logic.ILogic;
import net.vrgsoft.rollinglayoutmanager.logic.Logic;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;
import net.vrgsoft.rollinglayoutmanager.view.IMainActivity;
import net.vrgsoft.rollinglayoutmanager.view.NullMainActivity;

import java.util.List;

public class Presenter implements ILogic {
    private Logic logic;
    private IMainActivity MainActivity = new NullMainActivity();

    public Presenter(){
        logic = new Logic();
    }

    public void subscribe(IMainActivity im) {
        MainActivity = im;
    }

    public void unsubscribe(){
        MainActivity = new NullMainActivity();
    }

    public void inviaNickname(String nickname, Context c) {
        logic.getRepository(nickname, this, c);
    }

    @Override
    public void repo_disponibili(List<Repo> lista) {
        MainActivity.repo_presenti(lista);
    }

    @Override
    public void repo_non_disponibili() {

    }

    @Override
    public void errore() {

    }
}
