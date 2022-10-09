/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.khoahoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbchelper;

/**
 *
 * @author Thinkpad T490
 */
public class khoahocDAO extends edusysDAO<khoahoc, Integer> {

    String INSERT_SQL = "insert into KhoaHoc (maChuyenDe, hocPhi, thoiLuong, ngayKhaiGiang, ghiChu, makhoahoc, ngayTao) values(?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "update KhoaHoc set maChuyenDe = ?, hocPhi = ?, thoiLuong = ?, ngayKhaiGiang = ?, ghiChu = ?, makhoahoc = ?, ngayTao = ? where maKhoaHoc = ?";
    String DELETE_SQL = "delete from KhoaHoc where maKhoaHoc = ?";
    String SELECT_ALL_SQL = "select * from KhoaHoc";
    String SELECT_BY_ID_SQL = "select * from KhoaHoc where maChuyenDe = ?";

    @Override
    public void insert(khoahoc entity) {
        jdbchelper.update(INSERT_SQL,
                entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());
    }

    @Override
    public void update(khoahoc entity) {
        jdbchelper.update(UPDATE_SQL,
                entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao(),entity.getMaKH());
    }

    @Override
    public void delete(Integer key) {
        jdbchelper.update(DELETE_SQL, key);
    }

    @Override
    public List<khoahoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public khoahoc selectByid(Integer key) {
        List<khoahoc> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<khoahoc> selectBySql(String sql, Object... agrs) {
        List<khoahoc> list = new ArrayList<khoahoc>();
        try {
            ResultSet rs = jdbchelper.query(sql, agrs);
            while (rs.next()) {
                khoahoc entity = new khoahoc();
                entity.setMaKH(rs.getInt(""));
                entity.setMaCD(rs.getString(""));
                entity.setMaNV(rs.getString(""));
                entity.setHocPhi(rs.getDouble(""));
                entity.setThoiLuong(rs.getInt(""));
                entity.setNgayKG(rs.getDate(""));
                entity.setGhiChu(rs.getString(""));
                entity.setNgayTao(rs.getDate(""));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<khoahoc> selectByChuyenDe(String maCD) {
        String sql = "select * from KhoaHoc where maChuyenDe = ?";
        return this.selectBySql(sql, maCD);
    }

}
