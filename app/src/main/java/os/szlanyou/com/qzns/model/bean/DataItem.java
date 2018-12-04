package os.szlanyou.com.qzns.model.bean;

/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 便签数据
 */
public class DataItem {

    private int saveTime;
    private String title;
    private String content;

    public void setSaveTime(int saveTime) {
        this.saveTime = saveTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSaveTime() {
        return saveTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
