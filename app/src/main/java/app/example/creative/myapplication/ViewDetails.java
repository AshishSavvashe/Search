package app.example.creative.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewDetails extends AppCompatActivity {


    private String Version, Name, Api;
    private TextView tvdate;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        initViews();
    }

    private void initViews() {

        tvdate = (TextView) findViewById(R.id.tvdate);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.google.co.in/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        Bundle bundle = getIntent().getExtras();
        Version = bundle.getString("Version").trim();
        Name = bundle.getString("Name");
        Api = bundle.getString("Api");


        tvdate.setText(Version);
    }
}