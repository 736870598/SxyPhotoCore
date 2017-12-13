# SxyPhotoCore
该aar用于相册选择照片，相机拍照，jpeg压缩图片，查看图片， 加载图片，权限申请
等等功能。


## SxyUtilsManager

    /**
     * 选择照片
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     */
    public void selectPhoto(Activity act, int requestCode, boolean needCompress, String savePath)

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      保存路径
     */
    public void takePhoto(Activity act, int requestCode, boolean needCompress, String savePath)

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
     * @param bitmapPath    图片路径
     * @param savePath      保存路径
     * @param quality       压缩质量（默认30%）
     */
    public void compress(String bitmapPath, String savePath, int quality)

    /**
     * 压缩图片
     * @param bitmap    图片
     * @param savePath  保存路径
     * @param quality   压缩质量（默认30%）
     */
    public void compress(Bitmap bitmap, String savePath, int quality)

    /**
     * 申请权限
     * 最好放在onstart中请求。。。。
     *
     * @param activity      activity
     * @param listener      权限处理结果，未授权会重复请求，授权通过该回调传出
     * @param permissions   申请的权限 ps: Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    public void requestPermissions(Activity activity, RxPermissionsManager.PermissionDealListener listener, String...permissions)

## GildeUtils

    /**
     *  加载图片--
     *  @param fragment Fragment
     */
    public static void loadImage(Fragment fragment, String url, ImageView imageView)

    /**
     *  加载图片
     * @param activity  Activity
     */
    public static void loadImage(Activity activity, String url, ImageView imageView)

    /**
     *  加载图片
     *  @param context Context
     */
    public static void loadImage(Context context, String url, int w, int h, ImageView imageView)