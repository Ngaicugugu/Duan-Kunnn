/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.hocvien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbchelper;

/**
 *
 * @author Thinkpad T490
 */
public class hocvienDAO extends edusysDAO<hocvien, Integer>{
    String INSERT_SQL = "insert into HocVien (maKhoaHoc, maNguoiHoc, diemTrungBinh) values(?, ?, ?)";
    String UPDATE_SQL = "update HocVien set maKhoaHoc = ?, maNguoiHoc = ?, diemTrungBinh = ? where maHocVien =?";
    String DELETE_SQL = "delete from HocVien where maHocVien =?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE maHocVien=?";

    @Override
    public void insert(hocvien entity) {
        jdbchelper.update(INSERT_SQL,
                entity.getMaKH(),entity.getMaNH(),entity.getDiem());
    }

    @Override
    public void update(hocvien entity) {
        jdbchelper.update(UPDATE_SQL,
                entity.getMaKH(),entity.getMaNH(),entity.getDiem(),entity.getMaHV());
    }

    @Override
    public void delete(Integer key) {
        jdbchelper.update(DELETE_SQL, key);
    }

    @Override
    public List<hocvien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public hocvien selectByid(Integer key) {
        List<hocvien> list=this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<hocvien> selectBySql(String sql, Object... agrs) {
        List<hocvien> list=new ArrayList<hocvien>();
        try {
            ResultSet rs= jdbchelper.query(sql, agrs);
            while(rs.next()){
                hocvien entity=new hocvien();
                entity.setMaHV(rs.getInt("maHocVien"));
                entity.setMaKH(rs.getInt("maKhoaHoc"));
                entity.setMaNH(rs.getString("maNguoiHoc"));
                entity.setDiem(rs.getDouble("diemTrungBinh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
