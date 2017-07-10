package support1.t3h.com.crazymath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initComponents();
        setEvents();
    }

    private void findViewByIds() {
        ivPlay = (ImageView) findViewById(R.id.iv_play);
    }

    private void initComponents() {

    }

    private void setEvents() {
        ivPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        startActivity(intent);
    }
}
