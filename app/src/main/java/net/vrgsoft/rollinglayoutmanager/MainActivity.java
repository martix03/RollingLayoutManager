package net.vrgsoft.rollinglayoutmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

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
        presenter.inviaNickname("chemickypes", this);
    }

    private List<String> createTestData(int count) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            strings.add(UUID.randomUUID().toString());
        }

        return strings;
    }

    private List<String> createData(List<Repo> lista) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            strings.add(lista.get(i).getName());
        }

        return strings;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void repo_presenti(List<Repo> lista) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(createData(lista));
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
