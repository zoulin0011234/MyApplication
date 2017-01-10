package zoulin.bwf.com.dynamicpermissions;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOclick(View view) {
        switch (view.getId()) {
            case R.id.btn_quanxian:
                requestRuntimePermison(new String[]{Manifest.permission.CAMERA}, new PermissonListener() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(MainActivity.this, "权限已同意", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenide(List<String> denidePression) {
                        for (String permission : denidePression) {
                            Toast.makeText(MainActivity.this, permission + "权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            default:
                break;
        }
    }
}
