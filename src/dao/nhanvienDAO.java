/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.nhanvien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbchelper;

/**
 *
 * @author Thinkpad T490
 */
public class nhanvienDAO extends edusysDAO<nhanvien,String>{
    String INSERT_SQL="INSERT INTO NhanVien(maNhanVien,matKhau,hoTen,vaiTro) VALUES(?,?,?,?)";
    String UPDATE_SQL="UPDATE NhanVien SET matKhau=?, hoTen=?, vaiTro=? WHERE maNhanVien=?";
    String DELETE_SQL="DELETE FROM NhanVien WHERE maNhanVien=?";
    String SELECT_ALL_SQL="SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL="SELECT * FROM NhanVien WHERE maNhanVien=?";
    @Override
    public void insert(nhanvien entity) {
        jdbchelper.update(INSERT_SQL,
                entity.getMaNV(),entity.getHoTen(),entity.getMatKhau(),entity.isVaiTro());
    }

    @Override
    public void update(nhanvien entity) {
        jdbchelper.update(UPDATE_SQL,
                entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro(),entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        jdbchelper.update(DELETE_SQL, key);
    }

    @Override
    public List<nhanvien> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public nhanvien selectByid(String key) {
        List<nhanvien> list=this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<nhanvien> selectBySql(String sql, Object... agrs) {
        List<nhanvien> list=new ArrayList<nhanvien>();
        try {
            ResultSet rs= jdbchelper.query(sql, agrs);
            while(rs.next()){
                nhanvien entity=new nhanvien();
                entity.setMaNV(rs.getString("maNhanVien"));
                entity.setHoTen(rs.getString("hoTen"));
                entity.setMatKhau(rs.getString("matKhau"));
                entity.setVaiTro(rs.getBoolean("vaiTro"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
