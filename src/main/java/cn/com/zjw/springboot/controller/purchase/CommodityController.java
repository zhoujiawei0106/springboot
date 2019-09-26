package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 商品管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/commodity")
public class CommodityController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private CommodityService commodityService;

    /**
     * 获取商品列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Commodity commodity, HttpServletRequest request) {
        try {
            PageInfo pageInfo = commodityService.getCommoditys(commodity, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 保存商品信息
     * @author zhoujiawei
     * @param commodity
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Commodity commodity) {
        try {
            commodityService.save(commodity);
            return success("商品新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新商品信息
     * @author zhoujiawei
     * @param commodity
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Commodity commodity, javax.servlet.http.HttpServletRequest request) {
        try {
            commodityService.update(commodity, getUserId(getToken(request)));
            return success("客户修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getCustomer")
    public Map<String, Object> getCustomer(String id, javax.servlet.http.HttpServletRequest request) {
        try {
            Commodity commodity = commodityService.getCommodity(id, getUserId(getToken(request)));
            return success(commodity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id, javax.servlet.http.HttpServletRequest request) {
        try {
            commodityService.delete(id, getUserId(getToken(request)));
            return success("商品删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    @GetMapping("/export")
    public Map<String, Object> export(Commodity commodity, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Commodity> list = commodityService.export(commodity, getUserId(getToken(request)));
            ClassPathResource cpr = new ClassPathResource("templates/purchase/test.xls");
            InputStream is = cpr.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet0 =workbook.getSheetAt(0);
            for(int i = 0;i<list.size();i++){
                int num = 0;
                Row row = sheet0.createRow(i+3);//从第三行开始填充数据
                row.createCell(num).setCellValue(list.get(i).getName());
                row.createCell(num + 1).setCellValue(list.get(i).getDescription());
                row.createCell(num + 2).setCellValue(list.get(i).getPictureUrl());
                row.createCell(num + 3).setCellValue(list.get(i).getPrice().toString());
            }
            String fileName = "moban.xlsx";
            downLoadExcel(fileName, response, workbook);
            return success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }


    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
