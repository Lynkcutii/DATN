package org.example.module_banquanao.Repository;

import org.example.module_banquanao.Entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findByEmail(String email);

    // NhanVienRepository
    boolean existsByEmail(String email);
    boolean existsBySdt(String sdt);

//    @Query("SELECT nv FROM NhanVien nv " +
//            "WHERE (:keyword IS NULL OR (" +
//            "LOWER(nv.maNhanVien) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(nv.hoTen) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(nv.email) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
//            "AND (:trangThai IS NULL OR nv.trangThai = :trangThai)")
//    Page<NhanVien> searchNhanViens(@Param("keyword") String keyword,
//                                   @Param("trangThai") Boolean trangThai,
//                                   Pageable pageable);

}

