package cn.com.zjw.springboot.entity;

public class BaseEntity {

    private Integer page = 1;

    private Integer rows = 5;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
