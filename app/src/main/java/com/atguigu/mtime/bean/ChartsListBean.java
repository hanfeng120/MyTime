package com.atguigu.mtime.bean;

import java.util.ArrayList;

/**
 * 用来存储 排行榜的数据
 * <p/>
 * Created by 申瑞芹 on 2015/12/9.
 */
public class ChartsListBean {
    public int pageCount;
    public ArrayList<TopLists> topLists;
    public int totalCount;


    public class TopLists {
        public int id;
        public String summary;//描述
        public String topListNameCn;//标题
        public int type;

        public int getId() {
            return id;
        }

        public String getSummary() {
            return summary;
        }

        public String getTopListNameCn() {
            return topListNameCn;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return "TopLists{" +
                    "id=" + id +
                    ", summary='" + summary + '\'' +
                    ", topListNameCn='" + topListNameCn + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
