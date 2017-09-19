package ru.profs_13.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mCatTextView;
    CheckBox chb1, chb2;
    TextView textView;
    TextView textView2;
    TextView textView3;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    final int MENU_RESET = 7;
    final int MENU_RESET2 = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Это Снэкбар, я изменил тут текст", Snackbar.LENGTH_LONG)
                        .setAction("Действие с ним", null).show();
            }
        });

        chb1 = (CheckBox) findViewById(R.id.checkBox);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        registerForContextMenu(textView2);
        registerForContextMenu(textView3);

        mCatTextView = (TextView) findViewById(R.id.textViewCat);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                mSwipeRefreshLayout.setRefreshing(false);
                Random random = new Random();
                mCatTextView.setText("Котика пора кормить. Его не кормили уже "
                        + (1 + random.nextInt(10)) + " мин.");
            }
        }, 4000);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()) {
            case (R.id.textView2):
                menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
                menu.add(0, MENU_RESET, 0, "Reset");
                break;
            case (R.id.textView3):
                menu.add(0, MENU_SIZE_22, 0, "22");
                menu.add(0, MENU_SIZE_26, 0, "26");
                menu.add(0, MENU_SIZE_30, 0, "30");
                menu.add(0, MENU_RESET2, 0, "Reset");
                break;
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case MENU_COLOR_RED:
                textView2.setTextColor(Color.RED);
                textView2.setText("Text color = Red");
                break;
            case MENU_COLOR_GREEN:
                textView2.setTextColor(Color.GREEN);
                textView2.setText("Text color = GREEN");
                break;
            case MENU_COLOR_BLUE:
                textView2.setTextColor(Color.BLUE);
                textView2.setText("Text color = Blue");
                break;
            case MENU_RESET:
                textView2.setText(R.string.txtV2);
                textView2.setTextColor(Color.BLACK);
                break;

            case MENU_SIZE_22:
                textView3.setTextSize(22);
                textView3.setText("Text size = 22");
                break;
            case MENU_SIZE_26:
                textView3.setTextSize(26);
                textView3.setText("Text size = 26");
                break;
            case MENU_SIZE_30:
                textView3.setTextSize(30);
                textView3.setText("Text size = 30");
                break;
            case MENU_RESET2:
                textView3.setText(R.string.txtV3);
                textView3.setTextColor(Color.BLACK);
                textView3.setTextSize(14);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.setGroupVisible(R.id.group2, chb2.isChecked());
        menu.setGroupVisible(R.id.group1, chb1.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add(2, 4, 4, "item4").setCheckable(true);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case (R.id.action_settings):
                Toast.makeText(MainActivity.this, getString(R.string.action_settings), Toast.LENGTH_LONG).show();
                break;
            case (R.id.action_item1):
                Toast.makeText(MainActivity.this, getString(R.string.action_item1), Toast.LENGTH_LONG).show();
                break;
            case (R.id.action_item2):
                Toast.makeText(MainActivity.this, getString(R.string.action_item2), Toast.LENGTH_LONG).show();
                break;
            case (R.id.action_item3):
                Toast.makeText(MainActivity.this, getString(R.string.action_item3), Toast.LENGTH_LONG).show();
                break;
            case (R.id.action_item4):
                Toast.makeText(MainActivity.this, getString(R.string.action_item4), Toast.LENGTH_LONG).show();
                textView.setText("А текст может меняться, я же не просто так его сюда добавил");
                textView.setTextColor(getResources().getColor(R.color.forText));
                textView.setGravity(Gravity.CENTER);
                chb1.setText(R.string.chb1_new);
                chb1.setTextColor(getResources().getColor(R.color.magic));
                chb2.setTextColor(getResources().getColor(R.color.magic));
                chb2.setText(R.string.chb2_new);
                break;
            case 4:
                item.setChecked(!item.isChecked());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
