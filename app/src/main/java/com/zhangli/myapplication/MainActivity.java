package com.zhangli.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_id);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.list_normal) {
            loadListData(false,true);
            return true;
        }else if(id == R.id.list_vertical_reverse){
            loadListData(true,true);
            return true;
        }else if(id == R.id.list_horizontal){
            loadListData(false,false);
            return true;
        }else if(id == R.id.list_horizontal_reverse){
            loadListData(true,false);
            return true;
        }else if(id == R.id.grid_normal){
            loadGridData(false, true);
            return true;
        }else if(id == R.id.grid_vertical_reverse){
            loadGridData(true, true);
            return true;
        }else if(id == R.id.grid_horizontal){
            loadGridData(false, false);
            return true;
        }else if(id == R.id.grid_horizontal_reverse){
            loadGridData(true, false);
            return true;
        }else if(id == R.id.stagger_normal){
            loadStaggerData(false, true);
            return true;
        }else if(id == R.id.stagger_vertical_reverse){
            loadStaggerData(true, true);
            return true;
        }else if(id == R.id.stagger_horizontal){
            loadStaggerData(false, false);
            return true;
        }else if(id == R.id.stagger_horizontal_reverse){
            loadStaggerData(true, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载List的数据
     * @param isReverse 是否反向
     * @param isVertical 是否垂直
     */
    private void loadListData(boolean isReverse,boolean isVertical) {
        List<DataBean> datas=new ArrayList<>();

        for(int i=0;i<DATAS.ICNS.length;i++){
            DataBean bean=new DataBean();
            bean.icn=DATAS.ICNS[i];
            bean.name="图片"+i;
            datas.add(bean);
        }

        //给RecyclerView加载数据
        //1、设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        //设置是否反向
        linearLayoutManager.setReverseLayout(isReverse);

        //设置是否垂直显示
        linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);


        recyclerView.setLayoutManager(linearLayoutManager);
        //2、设置适配器
        recyclerView.setAdapter(new ListAdapter(this,datas));
    }

    /**
     * 表格布局
     * @param isReverse 是否反向
     * @param isVertical 是否垂直
     */
    public void loadGridData(boolean isReverse,boolean isVertical){

        List<DataBean> datas=new ArrayList<>();
        for(int i=0;i<DATAS.ICNS.length;i++){
            DataBean bean=new DataBean();
            bean.icn=DATAS.ICNS[i];
            bean.name="图片"+i;
            datas.add(bean);
        }

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);

        //是否反向
        gridLayoutManager.setReverseLayout(isReverse);

        //是否垂直
        gridLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        //2、设置适配器
        recyclerView.setAdapter(new GridAdapter(this, datas));

    }


    /**
     * 瀑布流显示
     * @param isReverse 是否反向
     * @param isVertical 是否垂直
     */
    public void loadStaggerData(boolean isReverse,boolean isVertical){

        List<DataBean> datas=new ArrayList<>();
        for(int i=0;i<DATAS.MEINVS.length;i++){
            DataBean bean=new DataBean();
            bean.icn=DATAS.MEINVS[i];
            bean.name="图片"+i;
            datas.add(bean);
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(2,
                        isVertical?StaggeredGridLayoutManager.VERTICAL
                                :StaggeredGridLayoutManager.HORIZONTAL);

        //是否反向
        staggeredGridLayoutManager.setReverseLayout(isReverse);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //2、设置适配器
        recyclerView.setAdapter(new StaggerAdapter(this, datas));

    }


}
