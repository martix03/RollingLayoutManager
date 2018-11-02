package net.vrgsoft.rollinglayoutmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import net.vrgsoft.layoutmanager.RollingLayoutManager;
import net.vrgsoft.rollinglayoutmanager.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RollingLayoutManager rollingLayoutManager = new RollingLayoutManager(this);
        SimpleAdapter simpleAdapter = new SimpleAdapter(createTestData(2));

        recyclerView.setLayoutManager(rollingLayoutManager);
        recyclerView.setAdapter(simpleAdapter);
    }

    private List<String> createTestData(int count) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            strings.add(UUID.randomUUID().toString());
        }

        return strings;
    }


    @Override
    public void repo_presenti() {

    }

    @Override
    public void repo_non_presenti() {

    }

    @Override
    public void errore() {

    }
}
