package zoulin.bwf.com.dynamicpermissions;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoulin on 2017/1/10.
 * 解决不在Activity中调用权限注册的问题
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 获取顶部Activity
     * @return
     */
    public static Activity getTopActivity(){
        if (activityList.isEmpty()){
            return  null;
        }else {
            return  activityList.get(activityList.size()-1);
        }
    }
}
