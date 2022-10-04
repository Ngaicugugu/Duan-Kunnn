/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.hocvien;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thinkpad T490
 */
public class HocVienDAO {

    public void insert(hocvien entity) {
        String sql = "INSERT INTO HocVien(maKhoaHoc, maNguoiHoc, diemTrungBinh) VALUES(?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    public void update(hocvien entity) {
        String sql = "UPDATE HocVien SET maKhoaHoc=?, maNguoiHoc=?, diemTrungBinh=? WHERE maHocVien=?";
        jdbcHelper.executeUpdate(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    public void delete(Integer MaHV) {
        String sql = "DELETE FROM HocVien WHERE maHocVien=?";
        jdbcHelper.executeUpdate(sql, MaHV);
    }

    public List<hocvien> select() {
        String sql = "SELECT * FROM HocVien";
        return select(sql);
    }

    public hocvien findById(Integer mahv) {
        String sql = "SELECT * FROM HocVien WHERE maHocVien=?";
        List<hocvien> list = select(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<hocvien> select(String sql, Object... args) {
        List<hocvien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    hocvien entity = readFromResultSet(rs);
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

    private hocvien readFromResultSet(ResultSet rs) throws SQLException {
        hocvien entity = new hocvien();
        entity.setMaHV(rs.getInt("maHocVien"));
        entity.setMaKH(rs.getInt("maKhoaHoc"));
        entity.setMaNH(rs.getString("maNguoiHoc"));
        entity.setDiem(rs.getDouble("diemTrungBinh"));
        return entity;
    }
}
