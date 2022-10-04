/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.nhanvien;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thinkpad T490
 */
public class NhanVienDAO {

    public void insert(nhanvien entity) {
        String sql = "INSERT INTO NhanVien (maNhanVien, matKhau , hoTen, vaiTro) VALUES (?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro());
    }

    public void update(nhanvien entity) {
        String sql = "UPDATE NhanVien SET matKhau=?, hoTen=?, vaiTro=? WHERE maNhanVien=?";
        jdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE maNhanVien=?";
        jdbcHelper.executeUpdate(sql, MaNV);
    }

    public List<nhanvien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    public nhanvien findById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien=?";
        List<nhanvien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<nhanvien> select(String sql, Object... args) {
        List<nhanvien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    nhanvien entity = readFromResultSet(rs);
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

    private nhanvien readFromResultSet(ResultSet rs) throws SQLException {
        nhanvien entity = new nhanvien();
        entity.setMaNV(rs.getString("maNhanVien"));
        entity.setMatKhau(rs.getString("matKhau"));
        entity.setHoTen(rs.getString("hoTen"));
        entity.setVaiTro(rs.getBoolean("vaiTro"));
        return entity;
    }
}
