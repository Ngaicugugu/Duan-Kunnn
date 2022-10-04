/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.nguoihoc;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thinkpad T490
 */
public class NguoiHocDAO {

    public void insert(nguoihoc entity) {
        String sql = "INSERT INTO NguoiHoc (maNguoiHoc, hoTen, ngaySinh, gioiTinh, soDT, Email, ghiChu, maNhanVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaNH(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.isGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV());
    }

    public void update(nguoihoc entity) {
        String sql = "UPDATE NguoiHoc SET hoTen=?, ngaySinh=?, gioiTinh=?, soDT=?, Email=?, ghiChu=?, maNhanVien = ? WHERE  maNguoiHoc = ? ";
        jdbcHelper.executeUpdate(sql,
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.isGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getMaNH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM NguoiHoc WHERE maNguoiHoc=?";
        jdbcHelper.executeUpdate(sql, id);
    }

    public List<nguoihoc> select() {
        String sql = "SELECT * FROM NguoiHoc";
        return select(sql);
    }

    public List<nguoihoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE hoTen LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

    public List<nguoihoc> selectByCourse(Integer makh) {
        String sql = "SELECT * FROM NguoiHoc WHERE maNguoiHoc NOT IN (SELECT maNguoiHoc FROM HocVien WHERE maKhoaHoc=?)";
        return select(sql, makh);
    }

    public nguoihoc findById(String manh) {
        String sql = "SELECT * FROM NguoiHoc WHERE maNguoiHoc=?";
        List<nguoihoc> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<nguoihoc> select(String sql, Object... args) {
        List<nguoihoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    nguoihoc entity = readFromResultSet(rs);
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

    private nguoihoc readFromResultSet(ResultSet rs) throws SQLException {
        nguoihoc entity = new nguoihoc();
        entity.setMaNH(rs.getString("maNguoiHoc"));
        entity.setHoTen(rs.getString("hoTen"));
        entity.setNgaySinh(rs.getDate("ngaySinh"));
        entity.setGioiTinh(rs.getBoolean("gioiTinh"));
        entity.setDienThoai(rs.getString("soDT"));
        entity.setEmail(rs.getString("Email"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setMaNV(rs.getString("maNhanVien"));
        entity.setNgayDK(rs.getDate("ngayDangKy"));
        return entity;
    }
}
