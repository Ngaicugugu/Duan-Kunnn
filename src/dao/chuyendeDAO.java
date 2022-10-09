/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.chuyende;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbchelper;

/**
 *
 * @author Thinkpad T490
 */
public class chuyendeDAO extends edusysDAO<chuyende, String> {

    String INSERT_SQL = "insert into ChuyenDe(maChuyenDe, tenChuyenDe, hocPhi, thoiLuong, hinhLogo, moTa) values(?, ?, ?, ? ,?, ?)";
    String UPDATE_SQL = "update ChuyenDe set tenChuyenDe = ?, hocPhi = ?, thoiLuong = ?, hinhLogo = ?, moTa = ? where maChuyenDe = ?";
    String DELETE_SQL = "delete from ChuyenDe where maChuyenDe = ?";
    String SELECT_ALL_SQL = "select * from ChuyenDe";
    String SELECT_BY_ID_SQL = "select * from ChuyenDe where maChuyenDe = ?";

    @Override
    public void insert(chuyende entity) {
        jdbchelper.update(INSERT_SQL,
                entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
    }

    @Override
    public void update(chuyende entity) {
        jdbchelper.update(UPDATE_SQL,
                entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(),entity.getMaCD());
    }

    @Override
    public void delete(String key) {
        jdbchelper.update(DELETE_SQL, key);
    }

    @Override
    public List<chuyende> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public chuyende selectByid(String key) {
        List<chuyende> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<chuyende> selectBySql(String sql, Object... agrs) {
        List<chuyende> list = new ArrayList<chuyende>();
        try {
            ResultSet rs = jdbchelper.query(sql, agrs);
            while (rs.next()) {
                chuyende entity = new chuyende();
                entity.setMaCD(rs.getString("maChuyenDe"));
                entity.setTenCD(rs.getString("tenChuyenDe"));
                entity.setHocPhi(rs.getDouble("hocPhi"));
                entity.setThoiLuong(rs.getInt("thoiLuong"));
                entity.setHinh(rs.getString("hinhLogo"));
                entity.setMoTa(rs.getString("moTa"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
