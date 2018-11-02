package net.vrgsoft.rollinglayoutmanager.view;

import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;

import java.util.List;

public class NullMainActivity implements IMainActivity {

    @Override
    public void repo_presenti(List<Repo> list) {

    }

    @Override
    public void repo_non_presenti() {

    }

    @Override
    public void errore() {

    }
}
