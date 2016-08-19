package business.task.service.impl;

import business.goods.service.GoodsService;
import business.order.service.TOrderService;
import business.task.entity.TaskEntity;
import business.task.service.TaskService;
import com.sys.service.impl.CommonService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends CommonService implements TaskService {


    @Resource
    private GoodsService goodsService;

    @Resource
    private TOrderService tOrderService;

    /**
     * 导入任务
     * @param taskFile
     * @return
     */
    @Override
    public String importTask(MultipartFile taskFile) {
        try{
            Workbook wb = WorkbookFactory.create(taskFile.getInputStream());
            Sheet sheet0 = wb.getSheetAt(0);    //刷单任务
            Sheet sheet1 = wb.getSheetAt(1);    //收货且评价任务
            Sheet sheet2 = wb.getSheetAt(2);    //收货任务
            Sheet sheet3 = wb.getSheetAt(3);    //评价任务
            List<TaskEntity> tasks =new ArrayList<>();
            tasks.addAll(getTask0(sheet0));
            tasks.addAll(getTask1(sheet1));
            tasks.addAll(getTask2(sheet2));
            tasks.addAll(getTask3(sheet3));
            for(TaskEntity task :tasks){
                this.save(task);
            }
        }catch (Exception e){
            return e.getMessage();
        }
        return "成功";
    }

    //刷单任务
    private List<TaskEntity> getTask0(Sheet sheet) throws Exception{
        List<TaskEntity> tasks = new ArrayList<>();
        int i=1;    //从第二行开始读
        while (true){
            Row row = sheet.getRow(i);
            if(row == null){
                break;
            }else {
                //SKU
                Cell SKUCell = row.getCell(0);
                if(SKUCell.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }
                String SKU = "";
                if(SKUCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    SKU = df.format(SKUCell.getNumericCellValue());
                }else {
                    SKU = SKUCell.getStringCellValue();
                }
                if(!this.goodsService.checkSKU(SKU)){
                    throw new Exception("SKU:"+SKU+"不存在");
                }
                //刷单方式
                Cell typeCell = row.getCell(1);
                String type = typeCell.getStringCellValue();
                //关键词
                Cell keyWorldCell = row.getCell(2);
                String keyWorld = keyWorldCell.getStringCellValue();
                //任务数
                Cell countCell = row.getCell(3);
                DecimalFormat df = new DecimalFormat("#");
                String countStr = df.format(countCell.getNumericCellValue());
                int taskCount = Integer.parseInt(countStr);
                //备注
                Cell markCell = row.getCell(4);
                String mark = markCell.getStringCellValue();
                for(int j = 0;j<taskCount;j++){
                    TaskEntity task = new TaskEntity();
                    task.setSku(SKU);
                    task.setSdfs(type);
                    task.setKeyword(keyWorld);
                    task.setMark(mark);
                    task.setTasktype("01");
                    tasks.add(task);
                }
            }
            i++;
        }
        return tasks;
    }
    //收货且评价任务
    private List<TaskEntity> getTask1(Sheet sheet) throws Exception{
        List<TaskEntity> tasks = new ArrayList<>();
        int i=1;    //从第二行开始读
        while (true){
            Row row = sheet.getRow(i);
            if(row == null){
                break;
            }else {
                //订单号
                Cell orderCell = row.getCell(0);
                if(orderCell.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }
                String orderNum = "";
                if(orderCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    orderNum = df.format(orderCell.getNumericCellValue());
                }else {
                    orderNum = orderCell.getStringCellValue();
                }
                if(!this.tOrderService.checkOrder(orderNum)){
                    throw new Exception("订单号："+orderNum+"不存在");
                }
                //评价文字
                Cell wordCell = row.getCell(1);
                String word = wordCell.getStringCellValue();
                //晒图编号
                Cell pictureCell = row.getCell(2);
                String picture = "";
                if(pictureCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    picture = df.format(pictureCell.getNumericCellValue());
                }else {
                    picture = pictureCell.getStringCellValue();
                }
                TaskEntity task = new TaskEntity();
                task.setOrdernun(orderNum);
                task.setPjwz(word);
                task.setPicture(picture);
                task.setTasktype("02");
                tasks.add(task);
            }
            i++;
        }
        return tasks;
    }
    //收货任务
    private List<TaskEntity> getTask2(Sheet sheet) throws Exception{
        List<TaskEntity> tasks = new ArrayList<>();
        int i=1;    //从第二行开始读
        while (true){
            Row row = sheet.getRow(i);
            if(row == null){
                break;
            }else {
                //订单号
                Cell orderCell = row.getCell(0);
                if(orderCell.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }
                String orderNum = "";
                if(orderCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    orderNum = df.format(orderCell.getNumericCellValue());
                }else {
                    orderNum = orderCell.getStringCellValue();
                }
                if(!this.tOrderService.checkOrder(orderNum)){
                    throw new Exception("订单号："+orderNum+"不存在");
                }
                TaskEntity task = new TaskEntity();
                task.setOrdernun(orderNum);
                task.setTasktype("03");
                tasks.add(task);
            }
            i++;
        }
        return tasks;
    }
    //评价任务
    private List<TaskEntity> getTask3(Sheet sheet) throws Exception{
        List<TaskEntity> tasks = new ArrayList<>();
        int i=1;    //从第二行开始读
        while (true){
            Row row = sheet.getRow(i);
            if(row == null){
                break;
            }else {
                //订单号
                Cell orderCell = row.getCell(0);
                if(orderCell.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }
                String orderNum = "";
                if(orderCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    orderNum = df.format(orderCell.getNumericCellValue());
                }else {
                    orderNum = orderCell.getStringCellValue();
                }
                if(!this.tOrderService.checkOrder(orderNum)){
                    throw new Exception("订单号："+orderNum+"不存在");
                }
                //评价文字
                Cell wordCell = row.getCell(1);
                String word = wordCell.getStringCellValue();
                //晒图编号
                Cell pictureCell = row.getCell(2);
                String picture = "";
                if(pictureCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    DecimalFormat df = new DecimalFormat("#");
                    picture = df.format(pictureCell.getNumericCellValue());
                }else {
                    picture = pictureCell.getStringCellValue();
                }
                TaskEntity task = new TaskEntity();
                task.setOrdernun(orderNum);
                task.setPjwz(word);
                task.setPicture(picture);
                task.setTasktype("04");
                tasks.add(task);
            }
            i++;
        }
        return tasks;
    }
}
