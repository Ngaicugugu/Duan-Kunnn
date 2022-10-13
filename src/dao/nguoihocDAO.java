/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.nguoihoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbchelper;

/**
 *
 * @author Thinkpad T490
 */
public class nguoihocDAO extends edusysDAO<nguoihoc,String>{
    String INSERT_SQL="INSERT INTO NguoiHoc(maNguoiHoc,maNhanVien,hoTen,ngaySinh,gioiTinh,soDT,Email,ghiChu,ngayDangKy) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL="update NguoiHoc set hoTen = ?, ngaySinh = ?, gioiTinh = ?, soDT = ?, Email = ? , ghiChu = ?, manguoihoc = ?, ngayDangKy = ? where maNguoiHoc = ?";
    String DELETE_SQL="delete from NguoiHoc where maNguoiHoc = ?";
    String SELECT_ALL_SQL="select * from NguoiHoc";
    String SELECT_BY_ID_SQL="SELECT * FROM NguoiHoc WHERE maNguoiHoc=?";
    
    @Override
    public void insert(nguoihoc entity) {
        jdbchelper.update(INSERT_SQL,
                entity.getMaNH(),entity.getMaNV(),entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),entity.getSoDT(),entity.getEmail(),entity.getGhiChu(),entity.getNgayDK());
    }

    @Override
    public void update(nguoihoc entity) {
        jdbchelper.update(UPDATE_SQL,
                entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),entity.getSoDT(),entity.getEmail(),entity.getGhiChu(),entity.getNgayDK(),entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        jdbchelper.update(DELETE_SQL, key);
    }

    @Override
    public List<nguoihoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public nguoihoc selectByid(String key) {
         List<nguoihoc> list=this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<nguoihoc> selectBySql(String sql, Object... agrs) {
        List<nguoihoc> list=new ArrayList<nguoihoc>();
        try {
            ResultSet rs= jdbchelper.query(sql, agrs);
            while(rs.next()){
                nguoihoc entity=new nguoihoc();
                entity.setMaNH(rs.getString("maNguoiHoc"));
                entity.setMaNV(rs.getString("maNhanVien"));
                entity.setHoTen(rs.getString("hoTen"));
                entity.setNgaySinh(rs.getDate("ngaySinh"));
                entity.setGioiTinh(rs.getBoolean("gioiTinh"));
                entity.setSoDT(rs.getString("soDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("ghiChu"));
                entity.setNgayDK(rs.getDate("ngayDangKy"));
                list.add(entity);
                
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<nguoihoc> selectByKeyword(String keyword){
        String sql = "select * from NguoiHoc where hoTen like ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    
}
