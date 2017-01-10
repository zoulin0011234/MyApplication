package zoulin.bwf.com.dynamicpermissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoulin on 2017/1/10.
 */

public class BaseActivity extends AppCompatActivity {
    private static PermissonListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    /**
     * 权限注册
     * @param premisons  我们注册的权限
     * @param lister      回调 负责给activity返回用户的操作
     */
    public static void requestRuntimePermison(String[] premisons, PermissonListener lister) {
        Activity toActivity=ActivityCollector.getTopActivity();
        if (toActivity==null){
            return;
        }
        mListener = lister;
        List<String> permissonList = new ArrayList<>();
        for (String premison : premisons) {
            if (ContextCompat.checkSelfPermission(toActivity, premison) != PackageManager.PERMISSION_GRANTED) {
                permissonList.add(premison);
            }
        }
        if (!permissonList.isEmpty()) {
            ActivityCompat.requestPermissions(toActivity, permissonList.toArray(new String[permissonList.size()]), 1);
        } else {
            //权限已经全部授权
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
          case 1:
            if (grantResults.length>0){
                List<String> denidePermission=new ArrayList<>();
                for (int i=0;i<grantResults.length;i++){
                    int grantResult=grantResults[i];
                    String perission=permissions[i];
                    if (grantResult!=PackageManager.PERMISSION_GRANTED){
                        denidePermission.add(perission);
                    }
                }
                if (denidePermission.isEmpty()){
                    mListener.onGranted();
                }else {
                    mListener.onDenide(denidePermission);
                }
            }
              break;
          default:
              break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
