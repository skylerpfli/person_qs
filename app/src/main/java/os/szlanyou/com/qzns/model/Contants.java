package os.szlanyou.com.qzns.model;

/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 存储常量
 */
public class Contants {

    //个人主页ListItem的字符串
    public final static String[] PERSON_LIST_DATA = new String[]{"回收站", "密码锁", "设置", "关于软件"};
    //个人主页list中回收站的位置
    public final static int RECLE_POSITION = 0;
    //个人主页list中密码锁的位置
    public final static int SWITCH_POSITION = 1;
    //个人主页list中设置的位置
    public final static int SETTING_POSITION = 2;
    //个人主页list中关于软件的位置
    public final static int ABOUT_POSITON = 3;


    //启动编辑界面，请求数据回调的请求码
    public final static int CODE_FOR_WRITE_RESULT = 0;
    //返回数据时，intent中的数据名称：是否需要刷新
    public final static String NAME_RESULT_DATA = "isNeedRefresh";

    //主界面标题所占用的字符串
    public final static int NUM_ITEM_TITLE_LENGTH = 17;


}
