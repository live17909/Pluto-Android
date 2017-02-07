package com.minggo.plutoandroidexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;
import com.minggo.pluto.activity.PlutoActivity;
import com.minggo.pluto.db.orm.FinalDb;
import com.minggo.pluto.util.PlutoFileCache;
import com.minggo.plutoandroidexample.R;
import com.minggo.plutoandroidexample.model.User;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by minggo on 2017/2/7.
 */
public class PlutoORMDBFrameworkExample extends PlutoActivity implements OnClickListener {

    @BindView(R.id.bt_save_db)
    public Button saveBt;
    @BindView(R.id.bt_acquire_db)
    public Button acquireBt;
    @BindView(R.id.bt_modify_db)
    public Button modfiyBt;
    @BindView(R.id.bt_delete_db)
    public Button deletet;

    private FinalDb finalDb;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pluto_ormdbframework_example);
        ButterKnife.bind(this);
        finalDb = FinalDb.create(this);
        user = new User();
        user.userId = 1000;
        user.username = "minggo";
        user.email = "minggo8en@gmail.com";
        finalDb.save(user);
    }


    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }


    @OnClick({R.id.bt_save_db,R.id.bt_acquire_db,R.id.bt_modify_db,R.id.bt_delete_db})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_save_db:
                finalDb.save(user);
                showToast("Data has been saved");
                break;
            case R.id.bt_acquire_db:

                User user1 = finalDb.findById(1000,User.class);
                showToast("Query username = "+user1.username);
                break;
            case R.id.bt_modify_db:
                user.username = "minggo8en";
                finalDb.update(user);
                showToast("Data has been modified");
                break;
            case R.id.bt_delete_db:
                finalDb.delete(user);
                showToast("Data has been deleted");
                break;
            default:
                break;
        }
    }
}