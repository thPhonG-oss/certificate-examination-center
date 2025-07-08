package com.pptk.certificate_examination_center.dao.impl;

import org.springframework.stereotype.Repository;

import com.pptk.certificate_examination_center.dao.DetailCandidateDao;
import com.pptk.certificate_examination_center.dto.DetailCandidateDto;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class DetailCandidateDaoImpl implements DetailCandidateDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DetailCandidateDto selectCandidateByRegistration(Integer registrationId, 
                                                            Integer certificateId) {
        String sql = """
                        SELECT 
                            ts.id_thi_sinh AS ma_thi_sinh,
                            cc.ten_chung_chi AS loai_chung_chi,
                            ts.ho_ten AS ten_thi_sinh,
                            ts.ngay_sinh,
                            ts.cccd,
                            LEFT(CONVERT(VARCHAR, lt.gio_thi, 108), 5) + ' - ' + CONVERT(VARCHAR, lt.ngay_thi, 103) AS lich_thi
                        FROM 
                            phieu_dang_ky pdk
                        JOIN 
                            thi_sinh ts ON ts.id_phieu_dang_ky = pdk.id_phieu_dang_ky
                        JOIN 
                            lich_thi lt ON lt.id_lich_thi = pdk.id_lich_thi
                        JOIN 
                            chung_chi cc ON cc.id_chung_chi = lt.id_chung_chi
                        WHERE 
                            pdk.id_phieu_dang_ky = ?
                            AND cc.id_chung_chi = ?;

                    """;
        try{
            Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, registrationId)
                .setParameter(2, certificateId)
                .getSingleResult();
            
            return new DetailCandidateDto(String.valueOf(result[0]),
                                            (String) result[1], 
                                            (String) result[2],
                                            String.valueOf(result[3]),
                                            (String) result[4],
                                            (String) result[5]);
        } catch (NoResultException e){
            return null;
        }
    }

}
