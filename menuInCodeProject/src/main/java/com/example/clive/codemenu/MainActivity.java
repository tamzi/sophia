package com.example.clive.codemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.LinearLayout;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */

public class MainActivity extends ActionBarActivity {

    private static final int MENU_ITEM = Menu.FIRST;
    private LinearLayout container;
    private MenuItem menuItemYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (LinearLayout) findViewById(R.id.container);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_ITEM, Menu.NONE, R.string.green);
        menu.add(0, MENU_ITEM + 1, Menu.NONE + 1, R.string.orange);
        menuItemYellow = menu.add(0, MENU_ITEM + 2,
                Menu.NONE + 2, R.string.yellow);

        SubMenu mySubMenu = menu.addSubMenu(Menu.NONE, Menu.NONE,
                101, R.string.sub_menu_title);
        mySubMenu.add(0, 4, 1, R.string.sub_menu_item_one);
        mySubMenu.add(0, 5, 2, R.string.sub_menu_item_two);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case MENU_ITEM:
                container.setBackgroundColor(getResources()
                        .getColor(R.color.green));
                return true;
            case MENU_ITEM + 1:
                container.setBackgroundColor(getResources()
                        .getColor(R.color.orange));
                return true;
            case 4:
                Intent intentGoOne = new Intent(this, ActivityOne.class);
                startActivity(intentGoOne);
                return true;
            case 5:
                Intent intentGoTwo = new Intent(this, ActivityTwo.class);
                startActivity(intentGoTwo);
                return true;
        }

        if (item == menuItemYellow) {
            container.setBackgroundColor(getResources()
                    .getColor(R.color.yellow));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
