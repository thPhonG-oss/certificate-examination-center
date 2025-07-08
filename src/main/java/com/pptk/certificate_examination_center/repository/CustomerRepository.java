package com.pptk.certificate_examination_center.repository;

import com.pptk.certificate_examination_center.dto.CustomerDto;
import com.pptk.certificate_examination_center.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT kh.id_khach_hang FROM khach_hang kh WHERE kh.email = :email", nativeQuery = true)
    public Long getCustomerIdByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM khach_hang WHERE cccd = :citizen_id", nativeQuery = true)
    public Customer findCustomerByCitizen_id(@Param("citizen_id") String citizen_id);


    @Modifying
    @Query(value = "UPDATE khach_hang SET " +
            "ho_ten = :name," +
            " ten_co_quan = :organization," +
            " sdt = :phone," +
            " email = :email," +
            " dia_chi = :address," +
            " cccd = :citizen_id," +
            " ngay_sinh = :dob" +
            " WHERE id_khach_hang = :id"
            , nativeQuery = true)
    public void updateCustomer(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("organization") String organization,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("address") String address,
            @Param("citizen_id") String citizen_id,
            @Param("dob") LocalDate birth_date
    );
}
