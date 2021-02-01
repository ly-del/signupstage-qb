package com.cb.signupstage.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ly
 * @time: 2021/1/28 16:31
 * @description:
 */
@Data
public class ExcelListener<T> extends  AnalysisEventListener<T>  {
    private final Logger logger = LoggerFactory.getLogger(ExcelListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Object> datas = new ArrayList<>();
    private static int count = 1;


    @Override
    public void invoke(T data, AnalysisContext context) {
        System.out.println("当前行:"+context.getCurrentRowNum()+",解析到一条数据:{ "+ data.toString() +" }");
        datas.add(data);
        count ++;
        if (datas.size() >= BATCH_COUNT) {
            saveData( count );
            datas.clear();
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData( count );
        System.out.println("所有数据解析完成！数量是:"+count);
        count = 0;
    }

    /**
     * 加上存储数据库
     */
    private void saveData(int count) {
        System.out.println("{ "+ count +" }条数据，开始存储数据库！" + datas.size());
        System.out.println("存储数据库成功！");

    }

    public List<Object> getDatas() {
        return datas;
    }

}
