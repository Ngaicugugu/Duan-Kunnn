/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.khoahoc;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thinkpad T490
 */
public class KhoaHocDAO {

    public void insert(khoahoc entity) {
        String sql = "INSERT INTO KhoaHoc (maChuyenDe, hocPhi, thoiLuong, ngayKhaiGiang, ghiChu, maNhanVien) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV());
    }

    public void update(khoahoc entity) {
        String sql ="UPDATE KhoaHoc SET maChuyenDe=?, hocPhi=?, hocPhi=?, ngayKhaiGiang=?, ghiChu=?, maNhanVien=? WHERE maKhoaHoc = ?";
 jdbcHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getMaKH());
    }

    public void delete(Integer MaKH) {
        String sql = "DELETE FROM KhoaHoc WHERE maKhoaHoc=?";
        jdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<khoahoc> select() {
        String sql = "SELECT * FROM KhoaHoc";
        return select(sql);
    }

    public khoahoc findById(Integer makh) {
        String sql = "SELECT * FROM KhoaHoc WHERE maKhoaHoc=?";
        List<khoahoc> list = select(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<khoahoc> select(String sql, Object... args) {
        List<khoahoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                   khoahoc entity = readFromResultSet(rs);
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

    private khoahoc readFromResultSet(ResultSet rs) throws SQLException {
        khoahoc entity = new khoahoc();
        entity.setMaKH(rs.getInt("maKhoaHoc"));
        entity.setHocPhi(rs.getDouble("hocPhi"));
        entity.setThoiLuong(rs.getInt("thoiLuong"));
        entity.setNgayKG(rs.getDate("ngayKhaiGiang"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setMaNV(rs.getString("maNhanVien"));
        entity.setNgayTao(rs.getDate("ngayTao"));
        entity.setMaCD(rs.getString("maChuyenDe"));

        return entity;
    }

}
