package com.pptk.certificate_examination_center.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pptk.certificate_examination_center.dao.ResultsDao;
import com.pptk.certificate_examination_center.dto.DetailResultsDto;
import com.pptk.certificate_examination_center.dto.ResultsExamDto;
import com.pptk.certificate_examination_center.mapper.DetailResultsMapper;
import com.pptk.certificate_examination_center.mapper.ResultsMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ResultsDaoImpl implements ResultsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResultsMapper resultsMapper;

    @Autowired
    private DetailResultsMapper detailResultsMapper;

    @Override
    public List<ResultsExamDto> findAllExamResults() {
        String sql = "SELECT ts.id_thi_sinh AS idThiSinh, cc.ten_chung_chi AS tenChungChi, kq.diem AS diem,kq.id_ket_qua " +
                "FROM ket_qua_thi kq " +
                "JOIN phieu_du_thi pd ON kq.id_phieu_du_thi = pd.id_phieu_du_thi " +
                "JOIN thi_sinh ts ON pd.id_thi_sinh = ts.id_thi_sinh " +
                "JOIN phieu_dang_ky pdk ON ts.id_phieu_dang_ky = pdk.id_phieu_dang_ky " +
                "JOIN chi_tiet_phieu_dang_ky ctpdk ON pdk.id_phieu_dang_ky = ctpdk.id_phieu_dang_ky " +
                "JOIN lich_thi lt ON ctpdk.id_lich_thi = lt.id_lich_thi " +
                "JOIN chung_chi cc ON lt.id_chung_chi = cc.id_chung_chi";

        Query query = entityManager.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return resultsMapper.mapToResultsDtoList(results);
    }

    @Override
    public List<ResultsExamDto> findExamResultsByThiSinhId(Integer idThiSinh) {
        String sql = "SELECT ts.id_thi_sinh AS idThiSinh, cc.ten_chung_chi AS tenChungChi, kq.diem AS diem,kq.id_ket_qua " +
                "FROM ket_qua_thi kq " +
                "JOIN phieu_du_thi pd ON kq.id_phieu_du_thi = pd.id_phieu_du_thi " +
                "JOIN thi_sinh ts ON pd.id_thi_sinh = ts.id_thi_sinh " +
                "JOIN phieu_dang_ky pdk ON ts.id_phieu_dang_ky = pdk.id_phieu_dang_ky " +
                "JOIN chi_tiet_phieu_dang_ky ctpdk ON pdk.id_phieu_dang_ky = ctpdk.id_phieu_dang_ky " +
                "JOIN lich_thi lt ON ctpdk.id_lich_thi = lt.id_lich_thi " +
                "JOIN chung_chi cc ON lt.id_chung_chi = cc.id_chung_chi " +
                "WHERE ts.id_thi_sinh = :idThiSinh";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idThiSinh", idThiSinh);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return resultsMapper.mapToResultsDtoList(results);
    }

    @Override
    public DetailResultsDto findDetailResultsByKetQuaId(Integer idKetQua) {
        String sql = "SELECT ts.ho_ten, ts.email, ts.sdt, cc.ten_chung_chi, lt.ngay_thi, " +
                "kq.diem, kq.trang_thai, kq.trang_thai_nhan, kq.id_ket_qua " +
                "FROM ket_qua_thi kq " +
                "JOIN phieu_du_thi pd ON kq.id_phieu_du_thi = pd.id_phieu_du_thi " +
                "JOIN thi_sinh ts ON pd.id_thi_sinh = ts.id_thi_sinh " +
                "JOIN phieu_dang_ky pdk ON ts.id_phieu_dang_ky = pdk.id_phieu_dang_ky " +
                "JOIN chi_tiet_phieu_dang_ky ctpdk ON pdk.id_phieu_dang_ky = ctpdk.id_phieu_dang_ky " +
                "JOIN lich_thi lt ON ctpdk.id_lich_thi = lt.id_lich_thi " +
                "JOIN chung_chi cc ON lt.id_chung_chi = cc.id_chung_chi " +
                "WHERE kq.id_ket_qua = :idKetQua";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idKetQua", idKetQua);

        Object[] result = (Object[]) query.getSingleResult();
        return detailResultsMapper.mapToDetailDto(result);
    }


    @Override
    @Transactional
    public void updateTrangThaiNhanById(Integer idKetQua, Boolean trangThaiNhan) {
        String sql = "UPDATE ket_qua_thi SET trang_thai_nhan = :trangThaiNhan WHERE id_ket_qua = :idKetQua";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("trangThaiNhan", trangThaiNhan ? 1 : 0); // Fix lỗi ép kiểu
        query.setParameter("idKetQua", idKetQua);
        query.executeUpdate();
    }


}