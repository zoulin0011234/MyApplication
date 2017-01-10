package zoulin.bwf.com.dynamicpermissions;

import java.util.List;

/**
 * Created by Zoulin on 2017/1/10.
 */

public interface PermissonListener {
    /**
     * 成功
     */
    void onGranted();

    /***
     * 失败
     *
     * @param denidePression
     */
    void onDenide(List<String> denidePression);
}
