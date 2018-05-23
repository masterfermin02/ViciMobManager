package com.viciexperts.fpapps.vicimobmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Campaigns extends AppCompatActivity {

   /* private CategorySqlRepository repository;
    private ListView listView;
    private  ListCategoryAdapter listCategoryAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);

        /*
        listView = (ListView) findViewById(R.id.listViewCategories);

        repository = new CategorySqlRepository(DbHelpers.getDbConnection(this));

        List<Category> listViewCategories = repository.query(new AllCategorySpecificacion());
        listCategoryAdapter = new ListCategoryAdapter(listViewCategories, this);
        listView.setAdapter(listCategoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent rCategory = new Intent(ListCategory.this, RegisterCategory.class);
                Category c = (Category) adapterView.getItemAtPosition(i);

                rCategory.putExtra("category", c);
                startActivity(rCategory);

            }
        });

         */
    }

  /*  @Override
    public void onResume(){
        super.onResume();

        listCategoryAdapter.setCategories(repository.query(new AllCategorySpecificacion()));
        listCategoryAdapter.notifyDataSetChanged();
    }*/
}
