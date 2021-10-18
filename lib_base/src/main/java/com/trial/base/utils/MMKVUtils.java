package com.trial.base.utils;

import com.tencent.mmkv.MMKV;

/**
 * <pre>
 *     @author : Trial
 *     @time   : 2021/07/08
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class MMKVUtils {
    private static volatile MMKVUtils instance;
    private final MMKV mmkv;

    private MMKVUtils() {
        mmkv = MMKV.defaultMMKV();
    }

    public static synchronized MMKVUtils getInstance() {
        if (instance == null) {
            synchronized (MMKVUtils.class) {
                if (instance == null) {
                    instance = new MMKVUtils();
                }
            }
        }

        return instance;
    }

    /**
     * 添加/更新数据
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        mmkv.encode(key, value);
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public void deleteKey(String key) {
        mmkv.removeValueForKey(key);
    }
}
