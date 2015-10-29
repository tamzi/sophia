package apps101.co.za.picassoandrecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */

public class MainActivity extends Activity {

    private RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private static MyRecyclerViewAdapter myAdapter;
    private ArrayList<AnArticle> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOnClickListener = new MyOnClickListener();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        articleList = getArticleData();

        myAdapter = new MyRecyclerViewAdapter(this, articleList);
        recyclerView.setAdapter(myAdapter);
    }


    private ArrayList<AnArticle> getArticleData() {
        articleList = new ArrayList<AnArticle>();
        for (int i = 0; i < MyArticleData.articles.length; i++) {
            String imageUrl = MyArticleData.articles[i][0];
            String articleUrl = MyArticleData.articles[i][1];

            articleList.add(new AnArticle(imageUrl, articleUrl));
        }
        return articleList;
    }


    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String selectedArticleUrl = getSelectedArticleUrl(v);
            showSelectedArticle(selectedArticleUrl);
        }
    }

    private String getSelectedArticleUrl(View view) {
        int selectedItemPosition = recyclerView.getChildPosition(view);
        String url = MyArticleData.articles[selectedItemPosition][1];
        return url;
    }

    private void showSelectedArticle(String articleUrl) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("articleUrl", articleUrl);
        startActivity(intent);
    }
}
