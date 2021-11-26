package com.trial.wanx

import com.drake.brv.utils.BRV
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.request.BaseRequest
import com.drake.statelayout.StateConfig
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.flattener.ClassicFlattener
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.Printer
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator
import com.elvishew.xlog.printer.file.writer.SimpleWriter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.trial.base.base.BaseApplication
import com.trial.base.constant.ConstantsUtils
import com.trial.base.constant.ConstantsUtils.logHeader
import com.trial.base.constant.PathConstants
import com.trial.base.constant.PathConstants.getStoragePath
import com.trial.base.http.GsonConvert
import com.trial.wanx.constant.UrlManager
import org.litepal.LitePal.initialize
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * <pre>
 * @author : Trial
 * @time   : 2021/07/07
 * @desc   :
 * @version: 1.0
</pre> *
 */
class TrialApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        //数据库初始化
        initialize(this)

        //代替SP的存储工具
        MMKV.initialize(this)

        // <editor-fold desc="XLog初始化">
        val config = LogConfiguration.Builder()
            .logLevel(LogLevel.ALL)
            .tag("Trail_WanX")
            .enableThreadInfo()
            .enableStackTrace(2)
            .enableBorder()
            .build()
        val androidPrinter: Printer = AndroidPrinter(true)
        val filePrinter: Printer =
            FilePrinter.Builder(getStoragePath(PathConstants.PATH_NAME_MOOR_LOG))
                .writer(object : SimpleWriter() {
                    override fun onNewFileCreated(file: File) {
                        appendLog(logHeader)
                    }
                })
                .fileNameGenerator(DateFileNameGenerator())
                .backupStrategy(NeverBackupStrategy())
                .cleanStrategy(FileLastModifiedCleanStrategy(ConstantsUtils.MAX_TIME.toLong()))
                .flattener(ClassicFlattener())
                .build()
        XLog.init(config, androidPrinter, filePrinter)
        // </editor-fold>

        // 初始化BindingAdapter的默认绑定ID
        BRV.modelId = BR.m

        //StateLayout
        StateConfig.apply {
            emptyLayout = R.layout.layout_state_empty
            errorLayout = R.layout.layout_state_error
            loadingLayout = R.layout.layout_state_loading

            setRetryIds(R.id.btn_reload)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.color_e6e6e6, R.color.color_666666) //全局设置主题颜色
            ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context).setDrawableSize(20f)
        }
        NetConfig.init(UrlManager.BASE_URL) {
            // 超时设置
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(20, TimeUnit.SECONDS)

            setConverter(GsonConvert())
            addInterceptor(LogRecordInterceptor(true))
            setRequestInterceptor(object : RequestInterceptor {
                override fun interceptor(request: BaseRequest) {
                    request.addHeader("app_id", "z8jmyfequkrdgkrm")
                    request.addHeader("app_secret", "K0ZYZm1hZTRLYTlUMjhSdW5vZFNHUT09")
                }
            })
        }
    }
}