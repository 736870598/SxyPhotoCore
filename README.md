# SxyUtilsCore
 用于选择照片，相机拍照，jpeg压缩图片，查看图片， 加载图片，权限申请，acticviry跳转等，所有功能都支持响应式编程，就像使用Retrofit一样使用。

### ------------------------------------------------------------------------------------------
##### 说明：

1. 如果使用拍照、选照或压缩功能，请提前声明好读写外置卡的权限
    
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        
2. 加载图片使用的是Gilde框架，默认缓存保存在context.getExternalCacheDir()/imageCache, 默认磁盘缓冲最大为100M，在7.0以上请在xm中声明该文件的使用权
 
        <external-cache-path path="imageCache" name="cache_root" />

3. 请务必在主module的build.gradle中引入：

        compile 'com.android.support:appcompat-v7:25.3.1'
        compile 'io.reactivex.rxjava2:rxjava:2.1.1'
        compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
        compile 'com.github.bumptech.glide:glide:4.0.0-RC0'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.0.0-RC0'

4. 图片压缩使用的是jpeg酷进行压缩的，请在主module的build.gradle中的defaultConfig标签下加入：

         ndk {
               abiFilters "armeabi","armeabi-v7a"
         }

5. 混淆问题不用担心，自带混淆文件。
### ------------------------------------------------------------------------------------------

## 核心类 UtilsCore

    /**
     * 选择照片
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     * @param callback      照片选择后回调函数
     */
    public void selectPicture(Activity act, int requestCode, boolean needCompress,
                                String savePath, ActivityResult.Callback callback)

    /**
     * 选择照片
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> selectPicture(Activity act, int requestCode,
                                                        boolean needCompress, String savePath)

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     * @param callback      照片选择后回调函数
     */
    public void takePicture(Activity act, int requestCode, boolean needCompress,
                                String savePath, ActivityResult.Callback callback)

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> takePicture(Activity act, int requestCode,
                                                        boolean needCompress, String savePath)

    /**
     * 申请权限 （直到用户做出最终选择。 《同意》 或 《点击了不再询问并拒绝了，而且还不去设置界面设置》）
     * @param act           activity
     * @param permissions   请求的权限
     * @return  RxJava使用Observable   true 有权限  false 没有权限
     */
    public Observable<Integer> requestPermissions(Activity act, String...permissions){
        return new ActivityResult(act, TypeEnum.TYPE_APPLY_PERMISSION).requestPermissions(permissions);
    }

    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param intent        跳转intent
     * @param callback      回调函数
     */
    public void startForResult(Activity act, int requestCode, Intent intent, ActivityResult.Callback callback)

    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param intent        跳转intent
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> startForResult(Activity act, int requestCode, Intent intent)

    /**
     * 查看图片
     * @param context   上下文
     * @param pathList  路径集合
     * @param seePos    查看图片位置
     */
    public void seePhoto(Context context, ArrayList<String> pathList, int seePos)

    /**
     * 查看图片
     * @param context   上下文
     * @param path      路径
     */
    public void seePhoto(Context context, String path)

    /**
     * 压缩图片
     * @param bitmap    图片
     * @param savePath  保存路径
     * @param quality   压缩质量（默认30%）
     */
    public void compress(Bitmap bitmap, String savePath, int quality)

    /**
     * 加载图片
     * @param context       上下文
     * @param url           图片地址
     * @param imageView     imageView
     */
    public void loadImage(Context context, String url, ImageView imageView)

    /**
     * 加载图片
     * @param context       上下文
     * @param url           图片地址
     * @param w             图片显示宽度
     * @param h             图片显示高度
     * @param imageView     imageView
     */
    public void loadImage(Context context, String url, int w, int h, ImageView imageView)

#### 其中ActivityResultInfo：

    public class ActivityResultInfo {
        //状态吗：0 完成  1 处理中  2 取消了  3 出错了
        private int state;
        //错误信息 当状态码为 3 时有值
        private String errorMsg;
        //返回的data
        private Intent data;
        //请求码
        private int requestCode;
    }

### 其他的工具类：

    SelectTimeUtils 选择时间工具类

    VersionUtils 版本信息工具类

    FileUtils 操作文件工具类

    StringUtils 字符串工具类

    ListUtils list工具类

    NetWorkUtils 网络工具类

    SharedPreferencesUtils SharedPreferences工具类

    TimeUtils  时间转换工具类

    ToastUtils toast工具类

    SystemUtils 系统相关的工具类

    Chinese2PyUtils 汉子转拼音攻工具类

    等等。。。
    