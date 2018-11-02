package net.vrgsoft.rollinglayoutmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import net.vrgsoft.layoutmanager.RollingLayoutManager;
import net.vrgsoft.rollinglayoutmanager.logic.datalayer.restservices.Repo;
import net.vrgsoft.rollinglayoutmanager.presenter.Presenter;
import net.vrgsoft.rollinglayoutmanager.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    Presenter presenter;
    RecyclerView recyclerView;
    RollingLayoutManager rollingLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        recyclerView = findViewById(R.id.recycler_view);
        rollingLayoutManager = new RollingLayoutManager(this);

        presenter = new Presenter();
        presenter.subscribe(this);
        presenter.inviaNickname("chemickypes", this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void repo_presenti(List<Repo> lista) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(lista);
        recyclerView.setLayoutManager(rollingLayoutManager);
        recyclerView.setAdapter(simpleAdapter);
    }

    @Override
    public void repo_non_presenti() {
    }

    @Override
    public void errore() {

    }
}
