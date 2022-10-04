/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.chuyende;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thinkpad T490
 */
public class ChuyenDeDAO {

    public void insert(chuyende entity) {
        String sql = "INSERT INTO ChuyenDe (maChuyenDe, tenChuyenDe, hocPhi, thoiLuong, hinhLogo, moTa) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa());
    }

    public void update(chuyende entity) {
        String sql = "UPDATE ChuyenDe SET tenChuyenDe=?, hocPhi=?, thoiLuong=?, hinhLogo=?, moTa=? WHERE maChuyenDe=?";
        jdbcHelper.executeUpdate(sql,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD());
    }

    public void delete(String MaCD) {
        String sql = "DELETE FROM ChuyenDe WHERE maChuyenDe=?";
        jdbcHelper.executeUpdate(sql, MaCD);
    }

    public List<chuyende> select() {
        String sql = "SELECT * FROM ChuyenDe";
        return select(sql);
    }

    public chuyende findById(String macd) {
        String sql = "SELECT * FROM ChuyenDe WHERE maChuyenDe=?";
        List<chuyende> list = select(sql, macd);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<chuyende> select(String sql, Object... args) {
        List<chuyende> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    chuyende entity = readFromResultSet(rs);
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private chuyende readFromResultSet(ResultSet rs) throws SQLException {
        chuyende entity = new chuyende();
        entity.setMaCD(rs.getString("maChuyenDe"));
        entity.setHinh(rs.getString("hinhLogo"));
        entity.setHocPhi(rs.getDouble("hocPhi"));
        entity.setMoTa(rs.getString("moTa"));
        entity.setTenCD(rs.getString("tenChuyenDe"));
        entity.setThoiLuong(rs.getInt("thoiLuong"));
        return entity;
    }

}
