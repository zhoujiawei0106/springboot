package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.constants.purchase.CustomerStatus;
import cn.com.zjw.springboot.constants.purchase.CustomerType;
import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/customer")
public class CustomerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Customer customer, HttpServletRequest request) {
        try {
            PageInfo pageInfo = customerService.getCustomers(customer, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 保存客户信息
     * @author zhoujiawei
     * @param customer
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Customer customer) {
        try {
            customerService.save(customer);
            return success("客户新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新客户信息
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Customer customer, HttpServletRequest request) {
        try {
            customerService.update(customer, getUserId(getToken(request)));
            return success("客户修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除客户信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id, HttpServletRequest request) {
        try {
            customerService.delete(id, getUserId(getToken(request)));
            return success("客户删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载客户信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getCustomer")
    public Map<String, Object> getCustomer(String id, HttpServletRequest request) {
        try {
            Customer customer = customerService.getCustomer(id, getUserId(getToken(request)));
            return success(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户类型常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/customerType")
    public Map<String, Object> customerType() {
        try {
            return success(CustomerType.getCustomerType());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户状态常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/customerStatus")
    public Map<String, Object> customerStatus() {
        try {
            return success(CustomerStatus.getCustomerStatus());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    @GetMapping("/export")
    public Map<String, Object> export(Customer customer, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Customer> list = customerService.export(customer, getUserId(getToken(request)));
            ClassPathResource cpr = new ClassPathResource("templates/purchase/test.xls");
            InputStream is = cpr.getInputStream();
//            InputStream is = this.getClass().getResourceAsStream("templates/purchase/test.xls");
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet0 =workbook.getSheetAt(0);
//            Row row = sheet0.getRow(2);
//            Cell cell0 = row.getCell(0);
//            Cell cell1 = row.getCell(1);
//            Cell cell2 = row.getCell(2);
//            cell0.setCellValue("guo");
//            cell1.setCellValue("bin");
//            cell2.setCellValue("hui");
//            System.out.println(cell0);
            for(int i = 0;i<list.size();i++){
                int num = 0;
                Row row = sheet0.createRow(i+3);//从第三行开始填充数据
                row.createCell(num).setCellValue(list.get(i).getName());
                row.createCell(num + 1).setCellValue(list.get(i).getNickName());
                row.createCell(num + 2).setCellValue(list.get(i).getAddress());
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
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
